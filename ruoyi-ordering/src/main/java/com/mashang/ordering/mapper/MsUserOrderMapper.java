package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsUserOrderMapper extends BaseMapper<MsOrder> {

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    public List<MsUserOrderListVo> getList(Long userId);

    /**
     * 根据订单id查询订单详情
     * @param orderId
     * @return
     */
    public MsUserOrderDtlVo getDtl(long orderId);
}
