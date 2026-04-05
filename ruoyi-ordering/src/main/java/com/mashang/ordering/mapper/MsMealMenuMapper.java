package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsMealMenu;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface MsMealMenuMapper extends BaseMapper<MsMealMenu> {

    /**
     * 批量新增套餐菜单信息
     * @param list
     * @return
     */
    int batchMealMenu(@Param("list") List<MsMealMenu> list);

    // 根据套餐ID删除菜单权限
    int deleteByMealId(Long mealId);

    // 查询套餐已绑定的菜单ID
    List<Long> selectMenuIdsByMealId(Long mealId);
}
