package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsUserOrderMapper extends BaseMapper<MsOrder> {
}
