package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.vo.MsSpecificationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsSpecificationMapper extends BaseMapper<MsSpecification> {

    List<MsSpecificationVo> getAllSpecifications(@Param("ew") Wrapper<MsSpecification> wrapper);

    MsSpecificationVo getSpecificationById(@Param("id") Long id);
}
