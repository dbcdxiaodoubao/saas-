package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsMealMenu;
import com.mashang.ordering.domain.param.create.MsMealCreate;
import com.mashang.ordering.domain.param.update.MsMealUpdate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;
import com.mashang.ordering.mapper.MsMealMapper;
import com.mashang.ordering.mapper.MsMealMenuMapper;
import com.mashang.ordering.mapping.MsMealMapping;
import com.mashang.ordering.service.IMsMealMenuService;
import com.mashang.ordering.service.IMsMealService;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MsMealServiceImpl extends ServiceImpl<MsMealMapper, MsMeal> implements IMsMealService {

    @Autowired
    private MsMealMenuMapper msMealMenuMapper;

    @Autowired
    private  MsMealMapper msMealMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertMeal(MsMealCreate msMealCreate) {

        MsMeal meal = new MsMeal();
        meal.setMealName(msMealCreate.getMealName());
        meal.setMealStatus(msMealCreate.getMealStatus());
        meal.setRemark(msMealCreate.getRemark());

        // 保存套餐
        this.save(meal);
        Long mealId = meal.getMealId();

        //获取菜单ID
        List<Long> menuIds = msMealCreate.getMenuIds();
        if (StringUtils.isEmpty(menuIds)) {
            return true;
        }

        //传父ID → 自动拿所有子菜单
        Set<Long> finalMenuIds = new HashSet<>();

        for (Long menuId : menuIds) {
            // 递归加入：当前菜单 + 所有子菜单（无限层级）
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

        msMealMenuMapper.batchMealMenu(saveList);

        return true;
    }

    //递归获取所有子菜单
    private void collectAllMenus(Long menuId, Set<Long> menuSet) {
        // 加入自己
        menuSet.add(menuId);

        // 查询子菜单
        LambdaQueryWrapper<SysMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.select(SysMenu::getMenuId);
        wrapper.eq(SysMenu::getParentId, menuId);

        List<SysMenu> children = sysMenuMapper.selectList(wrapper);

        // 递归加入子菜单
        for (SysMenu child : children) {
            collectAllMenus(child.getMenuId(), menuSet);
        }
    }

    @Override
    public MsMealDtlVo getMealDtl(Long mealId) {
        // 1. 获取套餐基本信息
        MsMealDtlVo meal = msMealMapper.getMealById(mealId);
        if (meal == null) {
            throw new ServiceException("套餐不存在");
        }

        // 2. 获取已勾选菜单ID
        List<Long> menuIds = msMealMenuMapper.selectMenuIdsByMealId(mealId);
        meal.setMenuIds(menuIds);

        return meal;
    }

    @Override
    public boolean updateMeal(MsMealUpdate msMealUpdate) {
        // 1. 修改套餐
        // 1. 先更新套餐基本信息
        MsMeal meal = new MsMeal();
        meal.setMealId(msMealUpdate.getMealId());
        meal.setMealName(msMealUpdate.getMealName());
        meal.setMealStatus(msMealUpdate.getMealStatus());
        meal.setRemark(msMealUpdate.getRemark());

        // 执行更新（自动填充 updateBy / updateTime）
        this.updateById(meal);

        // 2. 删除该套餐原来绑定的所有菜单
        LambdaQueryWrapper<MsMealMenu> delWrapper = Wrappers.lambdaQuery();
        delWrapper.eq(MsMealMenu::getMealId, msMealUpdate.getMealId());
        msMealMenuMapper.delete(delWrapper);

        // 3. 获取新的菜单ID
        List<Long> menuIds = msMealUpdate.getMenuIds();
        if (StringUtils.isEmpty(menuIds)) {
            return true;
        }

        // 4. 递归获取 父菜单 + 所有子菜单
        Set<Long> finalMenuIds = new HashSet<>();
        for (Long menuId : menuIds) {
            collectAllMenus(menuId, finalMenuIds);
        }

        // 5. 批量插入新的关联
        List<MsMealMenu> saveList = finalMenuIds.stream().map(id -> {
            MsMealMenu mm = new MsMealMenu();
            mm.setMealId(msMealUpdate.getMealId());
            mm.setMenuId(id);
            return mm;
        }).collect(Collectors.toList());

        msMealMenuMapper.batchMealMenu(saveList);

        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMeal(Long mealId) {

        Long count = sysUserMapper.selectCount(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getMealId, mealId)  // 租户表的套餐ID = 要删除的套餐ID
                        .eq(SysUser::getDelFlag, "0")    // 只查未删除的正常租户
        );

        if (count > 0) {
            throw new ServiceException("该套餐已经绑定租户，无法删除！");
        }

        boolean removeOk = this.removeById(mealId);

        msMealMenuMapper.delete(
                Wrappers.lambdaQuery(MsMealMenu.class)
                        .eq(MsMealMenu::getMealId, mealId)
        );

        return removeOk;
        }

    @Override
    public Map<String, Object> getMenuTree() {
        Map<String, Object> result = new HashMap<>();

        // 1. 查询新增的业务菜单（1061起，只查必要字段）
        List<SysMenu> menuList = sysMenuMapper.selectList(
                Wrappers.lambdaQuery(SysMenu.class)
                        .select(SysMenu::getMenuId, SysMenu::getMenuName, SysMenu::getParentId, SysMenu::getOrderNum)
                        .ge(SysMenu::getMenuId, 1061L)
                        .eq(SysMenu::getStatus, "0")
        );

        // 2. 构建正确排序的树形结构（父菜单永远在子菜单前面）
        List<Map<String, Object>> menuTree = buildTree(menuList);

        result.put("menuTree", menuTree);
        return result;
    }

    /**
     * 构建树形结构（强制父菜单在前，子菜单在后，按order_num排序）
     */
    private List<Map<String, Object>> buildTree(List<SysMenu> menuList) {
        List<Map<String, Object>> tree = new ArrayList<>();

        // 筛选根节点（parentId=0），按order_num升序
        List<SysMenu> rootNodes = menuList.stream()
                .filter(m -> m.getParentId() == 0)
                .sorted(Comparator.comparingInt(SysMenu::getOrderNum))
                .collect(Collectors.toList());

        for (SysMenu root : rootNodes) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", root.getMenuId());
            node.put("label", root.getMenuName());

            // 递归获取子节点
            List<Map<String, Object>> children = getChildren(root.getMenuId(), menuList);
            if (!children.isEmpty()) {
                node.put("children", children);
            }
            tree.add(node);
        }
        return tree;
    }

    /**
     * 递归获取子菜单（子节点内部按order_num排序）
     */
    private List<Map<String, Object>> getChildren(Long parentId, List<SysMenu> menuList) {
        List<Map<String, Object>> children = new ArrayList<>();

        // 筛选当前父节点的子菜单，按order_num升序
        List<SysMenu> childMenus = menuList.stream()
                .filter(m -> parentId.equals(m.getParentId()))
                .sorted(Comparator.comparingInt(SysMenu::getOrderNum))
                .collect(Collectors.toList());

        for (SysMenu menu : childMenus) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", menu.getMenuId());
            node.put("label", menu.getMenuName());

            // 递归获取孙节点
            List<Map<String, Object>> grandChildren = getChildren(menu.getMenuId(), menuList);
            if (!grandChildren.isEmpty()) {
                node.put("children", grandChildren);
            }
            children.add(node);
        }
        return children;
    }
}

