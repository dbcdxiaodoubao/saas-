package com.mashang.ordering.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.selete.MsTableOrderParam;
import com.mashang.ordering.domain.param.selete.MsUserOrderPageParam;
import com.mashang.ordering.domain.param.update.MsUserOrderUpdate;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.domain.vo.MsTableOrderDto;
import com.mashang.ordering.domain.vo.MsTableOrderListVo;
import com.mashang.ordering.domain.vo.MsUserOrderListPageVo;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

public interface IMsOrderService extends IService<MsOrder> {
    MsOrderDTO getMsOrderDate();

    ResultSet<Page<MsTableOrderListVo>> getMsTableOrderListVo(MsTableOrderParam msTableOrderParam, PageQuery pageQuery);

    ResultSet<MsTableOrderDto> getMsTableOrderDto(Long orderId,Long tableId);

    /**
     * 分页模糊查询订单列表
     * @param msUserOrderPageParam
     * @return
     */
    TableDataInfo<List<MsUserOrderListPageVo>> selectOrderPage(MsUserOrderPageParam msUserOrderPageParam);

    /**
     * 修改订单详情
     * @param msUserOrderUpdate
     * @return
     */
    ResultSet updateOrderDtl(MsUserOrderUpdate msUserOrderUpdate);
}
