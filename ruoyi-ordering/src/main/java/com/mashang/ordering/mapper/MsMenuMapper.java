package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.vo.MsMenuChildListVo;
import com.mashang.ordering.domain.vo.MsMenuListVo;
import com.ruoyi.common.core.domain.entity.SysMenu;

public interface MsMenuMapper extends BaseMapper<SysMenu> {

    // 根据套餐ID查父菜单
    MsMenuListVo getParentMenuByMealId(Long mealId);

    // 根据父菜单ID查子菜单
    MsMenuChildListVo getChildMenuByParentId(Long parentId);

}
