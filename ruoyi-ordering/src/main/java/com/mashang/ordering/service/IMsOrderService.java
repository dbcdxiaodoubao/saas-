package com.mashang.ordering.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.vo.MsOrderDTO;

public interface IMsOrderService extends IService<MsOrder> {
    MsOrderDTO getMsOrderDate();
}
