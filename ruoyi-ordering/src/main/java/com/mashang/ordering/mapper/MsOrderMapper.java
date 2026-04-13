package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.vo.MsTableOrderDto;
import com.mashang.ordering.domain.vo.MsTableOrderListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface MsOrderMapper extends BaseMapper<MsOrder> {

    @Select("SELECT IFNULL(SUM(actual_pay), 0) FROM ms_order WHERE order_status = 1 AND del_flag = 0")
    BigDecimal getTotalAmount();

    List<MsTableOrderListVo> getMsTableOrderListVo(@Param("ew") QueryWrapper<Object> qw);

    MsTableOrderDto getMsTableOrderDto(@Param("ew") QueryWrapper<Object> qw);
}
