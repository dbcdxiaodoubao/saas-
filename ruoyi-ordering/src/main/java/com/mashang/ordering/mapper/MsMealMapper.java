package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.param.update.MsMealUpdate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;

public interface MsMealMapper extends BaseMapper<MsMeal> {

    // 根据ID获取套餐详情（含菜单）
    MsMealDtlVo getMealById(Long mealId);

    // 修改套餐基本信息
    int updateMeal(MsMealUpdate msMealUpdate);
}
