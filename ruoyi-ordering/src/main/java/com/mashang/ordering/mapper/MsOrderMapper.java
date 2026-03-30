package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsOrder;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

public interface MsOrderMapper extends BaseMapper<MsOrder> {

    @Select("SELECT IFNULL(SUM(pay_amount), 0) FROM order_info WHERE pay_status = 1 AND del_flag = 0")
    BigDecimal getTotalAmount();
}
