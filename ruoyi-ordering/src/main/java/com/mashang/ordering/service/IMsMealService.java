package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsMealMenu;
import com.mashang.ordering.domain.param.create.MsMealCreate;
import com.mashang.ordering.domain.param.update.MsMealUpdate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface IMsMealService extends IService<MsMeal> {

    /**
     * 批量新增套餐菜单信息
     * @param msMealCreate
     * @return
     */
    boolean insertMeal(MsMealCreate msMealCreate);

    /**
     * 查询租户套餐信息详情
     * @param mealId
     * @return
     */
    MsMealDtlVo getMealDtl(Long mealId);

    /**
     * 修改租户套餐信息
     * @param msMealUpdate
     * @return
     */
    boolean updateMeal(MsMealUpdate msMealUpdate);

    /**
     * 删除租户套餐信息
     * @param mealId
     * @return
     */
     boolean deleteMeal(Long mealId);
}
