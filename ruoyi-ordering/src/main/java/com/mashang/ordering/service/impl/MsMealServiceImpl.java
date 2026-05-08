package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsMealMenu;
import com.mashang.ordering.domain.param.create.MsMealCreate;
import com.mashang.ordering.domain.param.update.MsMealUpdate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;
import com.mashang.ordering.domain.vo.MsMenuChildListVo;
import com.mashang.ordering.domain.vo.MsMenuListVo;
import com.mashang.ordering.mapper.MsMealMapper;
import com.mashang.ordering.mapper.MsMealMenuMapper;
import com.mashang.ordering.mapper.MsMenuMapper;
import com.mashang.ordering.service.IMsMealService;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MsMealServiceImpl extends ServiceImpl<MsMealMapper, MsMeal> implements IMsMealService {

    @Autowired
    private MsMealMenuMapper msMealMenuMapper;

    @Autowired
    private MsMealMapper msMealMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private MsMenuMapper msMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMeal(MsMealCreate msMealCreate) {

        MsMeal meal = new MsMeal();
        meal.setMealName(msMealCreate.getMealName());
        meal.setMealStatus(msMealCreate.getMealStatus());
        meal.setRemark(msMealCreate.getRemark());

        // 保存套餐
        int mealResult = msMealMapper.insert(meal);
        Long mealId = meal.getMealId();

        //获取菜单ID
        List<Long> menuIds = msMealCreate.getMenuIds();
        if (StringUtils.isEmpty(menuIds)) {
            return mealResult;
        }

        //传父ID → 自动拿所有子菜单
        Set<Long> finalMenuIds = new HashSet<>();
        for (Long menuId : menuIds) {
            collectAllMenus(menuId, finalMenuIds);
        }

        // 批量插入
        List<MsMealMenu> saveList = new ArrayList<>();
        for (Long id : finalMenuIds) {
            MsMealMenu mm = new MsMealMenu();
            mm.setMealId(mealId);
            mm.setMenuId(id);
            saveList.add(mm);
        }

        return msMealMenuMapper.batchMealMenu(saveList);
    }

    //递归获取所有子菜单
    private void collectAllMenus(Long menuId, Set<Long> menuSet) {
        menuSet.add(menuId);
        LambdaQueryWrapper<SysMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.select(SysMenu::getMenuId);
        wrapper.eq(SysMenu::getParentId, menuId);
        List<SysMenu> children = sysMenuMapper.selectList(wrapper);
        for (SysMenu child : children) {
            collectAllMenus(child.getMenuId(), menuSet);
        }
    }

    @Override
    public MsMealDtlVo getMealDtl(Long mealId) {
        // 1. 直接获取【套餐 + 父菜单 + 子菜单】三级结构（XML 已经全部搞定）
        MsMealDtlVo meal = msMealMapper.getMealById(mealId);
        if (meal == null) {
            throw new ServiceException("套餐不存在");
        }

        // 2. 设置菜单ID集合
        List<Long> menuIds = msMealMenuMapper.selectMenuIdsByMealId(mealId);
/*        meal.setMenuIds(menuIds);*/
        return meal;
    }

    @Override
    public int updateMeal(MsMealUpdate msMealUpdate) {
        // 1. 修改套餐
        MsMeal meal = new MsMeal();
        meal.setMealId(msMealUpdate.getMealId());
        meal.setMealName(msMealUpdate.getMealName());
        meal.setMealStatus(msMealUpdate.getMealStatus());
        meal.setRemark(msMealUpdate.getRemark());

        // 2. 删除该套餐原来绑定的所有菜单
        LambdaQueryWrapper<MsMealMenu> delWrapper = Wrappers.lambdaQuery();
        delWrapper.eq(MsMealMenu::getMealId, msMealUpdate.getMealId());
        msMealMenuMapper.delete(delWrapper);

        // 3. 获取新的菜单ID
        List<Long> menuIds = msMealUpdate.getMenuIds();
        if (StringUtils.isEmpty(menuIds)) {
            return msMealMapper.updateById(meal);
        }

        // 4. 递归获取 父菜单 + 所有子菜单
        Set<Long> finalMenuIds = new HashSet<>();
        for (Long menuId : menuIds) {
            collectAllMenus(menuId, finalMenuIds);
        }

        // 5. 批量插入新的关联
        List<MsMealMenu> saveList = new ArrayList<>();
        for (Long id : finalMenuIds) {
            MsMealMenu mm = new MsMealMenu();
            mm.setMealId(msMealUpdate.getMealId());
            mm.setMenuId(id);
            saveList.add(mm);
        }

        // 先更新套餐
        msMealMapper.updateById(meal);

        return msMealMenuMapper.batchMealMenu(saveList);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMeal(Long mealId) {

        Long count = sysUserMapper.selectCount(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getMealId, mealId)
                        .eq(SysUser::getDelFlag, "0")
        );

        if (count > 0) {
            throw new ServiceException("该套餐已经绑定租户，无法删除！");
        }

        // 删除绑定关系
        msMealMenuMapper.delete(
                Wrappers.lambdaQuery(MsMealMenu.class)
                        .eq(MsMealMenu::getMealId, mealId)
        );

        return msMealMapper.deleteById(mealId);
    }

    @Override
    public Map<String, Object> getMenuTree() {
        Map<String, Object> result = new HashMap<>();
        List<SysMenu> menuList = sysMenuMapper.selectList(
                Wrappers.lambdaQuery(SysMenu.class)
                        .select(SysMenu::getMenuId, SysMenu::getMenuName, SysMenu::getParentId, SysMenu::getOrderNum)
                        .ge(SysMenu::getMenuId, 1061L)
                        .eq(SysMenu::getStatus, "0")
        );

        List<Map<String, Object>> menuTree = buildTree(menuList);
        result.put("menuTree", menuTree);
        return result;
    }

    private List<Map<String, Object>> buildTree(List<SysMenu> menuList) {
        List<Map<String, Object>> tree = new ArrayList<>();

        // 1. 筛选根节点（parentId=0）
        List<SysMenu> rootNodes = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId() == 0) {
                rootNodes.add(menu);
            }
        }

        // 排序
        rootNodes.sort(Comparator.comparingInt(SysMenu::getOrderNum));

        // 3. 构建树形结构
        for (SysMenu root : rootNodes) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", root.getMenuId());
            node.put("label", root.getMenuName());

            List<Map<String, Object>> children = getChildren(root.getMenuId(), menuList);
            if (!children.isEmpty()) {
                node.put("children", children);
            }

            tree.add(node);
        }
        return tree;
    }

    private List<Map<String, Object>> getChildren(Long parentId, List<SysMenu> menuList) {
        List<Map<String, Object>> children = new ArrayList<>();

        // 1. 筛选子菜单
        List<SysMenu> childMenus = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (parentId.equals(menu.getParentId())) {
                childMenus.add(menu);
            }
        }

        // 2. 排序
        childMenus.sort(Comparator.comparingInt(SysMenu::getOrderNum));

        // 3. 循环构建节点
        for (SysMenu menu : childMenus) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", menu.getMenuId());
            node.put("label", menu.getMenuName());

            List<Map<String, Object>> grandChildren = getChildren(menu.getMenuId(), menuList);
            if (!grandChildren.isEmpty()) {
                node.put("children", grandChildren);
            }
            children.add(node);
        }
        return children;
    }
}