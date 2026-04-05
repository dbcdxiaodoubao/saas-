package com.mashang.ordering.service.impl;

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
import com.mashang.ordering.service.IMsMealService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MsMealServiceImpl extends ServiceImpl<MsMealMapper, MsMeal> implements IMsMealService {

    @Autowired
    private MsMealMenuMapper msMealMenuMapper;

    @Autowired
    private  MsMealMapper msMealMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public boolean insertMeal(MsMealCreate msMealCreate) {
        boolean save = save(MsMealMapping.INSTANCE.toCreate(msMealCreate));

        // 批量插入菜单权限
        if (save && StringUtils.isNotNull(msMealCreate.getMenuIds())) {
            List<MsMealMenu> list = new ArrayList<>();
            for (Long menuId : msMealCreate.getMenuIds()) {
                MsMealMenu msMealMenu = new MsMealMenu();
                msMealMenu.setMenuId(menuId);
                list.add(msMealMenu);
            }
            msMealMenuMapper.batchMealMenu(list);
        }
        return save;
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
        int rows = msMealMapper.updateMeal(msMealUpdate);

        // 2. 删除旧权限
        msMealMenuMapper.deleteByMealId(msMealUpdate.getMealId());

        // 3. 批量插入新权限
        if (msMealUpdate.getMenuIds() != null && !msMealUpdate.getMenuIds().isEmpty()) {
            List<MsMealMenu> list = new ArrayList<>();
            for (Long menuId : msMealUpdate.getMenuIds()) {
                MsMealMenu mm = new MsMealMenu();
                mm.setMealId(msMealUpdate.getMealId());
                mm.setMenuId(menuId);
                list.add(mm);
            }
            msMealMenuMapper.batchMealMenu(list);
        }

        return rows > 0;
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
    }

