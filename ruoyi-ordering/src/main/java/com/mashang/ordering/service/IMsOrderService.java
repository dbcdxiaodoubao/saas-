package com.mashang.ordering.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.selete.MsTableOrderParam;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.domain.vo.MsTableOrderDto;
import com.mashang.ordering.domain.vo.MsTableOrderListVo;

public interface IMsOrderService extends IService<MsOrder> {
    MsOrderDTO getMsOrderDate();

    ResultSet<Page<MsTableOrderListVo>> getMsTableOrderListVo(MsTableOrderParam msTableOrderParam, PageQuery pageQuery);

    ResultSet<MsTableOrderDto> getMsTableOrderDto(Long orderId,Long tableId);
}
