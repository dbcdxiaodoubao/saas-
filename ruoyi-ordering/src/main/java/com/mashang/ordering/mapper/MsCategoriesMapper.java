package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsCategories;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsCategoriesMapper extends BaseMapper<MsCategories> {

    @Select("SELECT LAST_INSERT_ID()")
    Long getLastInsertId();

    List<MsCategoriesListVo> getAllCategories(@Param("MsCategoriesName") String MsCategoriesName,
                                              @Param("MsStoreName") String MsStoreName);
}
