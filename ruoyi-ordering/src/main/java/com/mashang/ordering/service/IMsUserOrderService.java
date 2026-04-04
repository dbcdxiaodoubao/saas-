package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;

import java.util.List;

public interface IMsUserOrderService extends IService<MsOrder> {

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

    /**
     * 创建新订单
     * @param create
     */
    void insertOrder(MsUserOrderCreate create);
}
