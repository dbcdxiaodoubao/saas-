package com.mashang.ordering.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsOrderDetail;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.create.MsUserOrderAdd;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.param.create.MsUserOrderProductCreate;
import com.mashang.ordering.domain.param.selete.MsUserOrderPageParam;
import com.mashang.ordering.domain.param.update.MsUserOrderDetailUpdate;
import com.mashang.ordering.domain.param.update.MsUserOrderUpdate;
import com.mashang.ordering.domain.vo.*;
import com.mashang.ordering.mapper.*;
import com.mashang.ordering.mapping.MsProductMapping;
import com.mashang.ordering.service.IMsUserOrderService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MsUserOrderServiceImpl extends ServiceImpl<MsUserOrderMapper, MsOrder> implements IMsUserOrderService {

    @Autowired
    private MsUserOrderMapper msUserOrderMapper;

    @Autowired
    private MsStoreMapper msStoreMapper;

    @Autowired
    private MsOrderDetailMapper msOrderDetailMapper;

    @Autowired
    private MsOrderMapper msOrderMapper;

    @Override
    public List<MsUserOrderListVo> getList(Long userId) {
        return msUserOrderMapper.getList(userId);
    }

    @Override
    public MsUserOrderDtlVo getDtl(long orderId) {
        MsUserOrderDtlVo dtl = msUserOrderMapper.getDtl(orderId);

        dtl.setTableNumber(msUserOrderMapper.getLabelNumber(orderId));
        return dtl;
    }


    public void insertOrder(MsUserOrderCreate create) {

        String orderNumber = String.valueOf(System.currentTimeMillis());

        String pickupNumber = generate4DigitDailyOrderNo(create.getStoreId());

        create.setOrderNumber(orderNumber);
        create.setPickupNumber(pickupNumber);
        List<MsUserOrderProductCreate> productList = create.getMsUserOderProductCreateList();
        double total = 0;
        for (MsUserOrderProductCreate product : productList) {
            total += product.getProductPrice() * product.getProductQuantity();
        }
        create.setProductTotalPrice(total);

        msUserOrderMapper.insertOrder(create);

        Long orderId = msUserOrderMapper.getOrderIdByOrderNumberLong(orderNumber);

        msUserOrderMapper.batchInsertOrderDetail(orderId, productList);
    }

    @Override
    public void addProduct(MsUserOrderAdd msUserOrderAdd) {
        Long addCount = msUserOrderMapper.selectAddCount(msUserOrderAdd.getOrderId())+1;

        List<MsUserOrderProductCreate> productList = msUserOrderAdd.getMsUserOderProductCreateList();

        for (MsUserOrderProductCreate product : productList) {
            product.setCumulativeAddCount(addCount);
        }

        msUserOrderMapper.batchInsertOrderDetail(msUserOrderAdd.getOrderId(), productList);
    }

    @Override
    public void pay(Long orderId,String totalAmount) {
        msUserOrderMapper.pay(orderId,totalAmount);
    }

    @Override
    public void updateOrderStatusToRefund(Long orderId) {
        msUserOrderMapper.updateOrderStatusToRefund(orderId);
    }

    @Override
    public List<MsUserTableListVo> getLabelId(Long storeId) {
        return msUserOrderMapper.getLabelId(storeId);
    }

    @Override
    public void userRecharge(Long userId, BigDecimal money) {
        msUserOrderMapper.userRecharge(userId,money);
    }


    private String generate4DigitDailyOrderNo(Long storeId) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LambdaQueryWrapper<MsOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply("DATE(create_time) = {0}", today)
                .eq(MsOrder::getStoreId, storeId)  // 关键：按当前商店筛选
                .orderByDesc(MsOrder::getOrderNumber)
                .last("LIMIT 1");

        MsOrder last = msUserOrderMapper.selectOne(wrapper);
        int nextSeq = 1;

        if (last != null && last.getOrderNumber() != null) {
            try {
                int maxSeq = Integer.parseInt(last.getOrderNumber());
                nextSeq = maxSeq + 1;
            } catch (Exception ignored) {}
        }

        if (nextSeq > 9999) nextSeq = 1;
        return String.format("%04d", nextSeq);
    }

    @Override
    public TableDataInfo<List<MsUserOrderListPageVo>> selectOrderPage(MsUserOrderPageParam msUserOrderPageParam) {

        //根据店主的商店数来返回的订单
        LoginUser loginUser = SecurityUtils.getLoginUser();
        LambdaQueryWrapper<MsStore> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MsStore::getUserId, loginUser.getUserId());
        wrapper.eq(MsStore::getDelFlag, 0);
        List<MsStore> msStores = msStoreMapper.selectList(wrapper);
        msUserOrderPageParam.setStoreIds(msStores.stream().map(MsStore::getStoreId).collect(Collectors.toList()));
        System.out.println(msUserOrderPageParam);

        PageHelper.startPage(msUserOrderPageParam.getPageNum(), msUserOrderPageParam.getPageSize());

        List<MsUserOrderListPageVo> msUserOrderListPageVos = msUserOrderMapper.selectOrderPage(msUserOrderPageParam);
        TableDataInfo<List<MsUserOrderListPageVo>> tableDataInfo = new TableDataInfo<>();
        tableDataInfo.setTotal(msUserOrderListPageVos.size());
        tableDataInfo.setRows(msUserOrderListPageVos);

        if(!msUserOrderListPageVos.isEmpty()){
            tableDataInfo.setCode(200);
            tableDataInfo.setMsg("查询成功");
            return tableDataInfo;
        }

        tableDataInfo.setCode(500);
        tableDataInfo.setMsg("查询失败");
        return tableDataInfo;

    }

    @Override
    @Transactional
    public ResultSet updateOrderDtl(MsUserOrderUpdate update) {

        List<MsUserOrderDetailUpdate> userOderProductDtlList = update.getMsUserOrderDetailUpdates();

        //修改订单详情表
        //如果商品全是已出单，修改订单状态为2已完成
        String isDone = "1";
        List<MsOrderDetail> msOrderDetailList = MsProductMapping.INSTANCE.toMsOrderDetailList(userOderProductDtlList);

        for (MsOrderDetail msOrderDetail : msOrderDetailList) {
            msOrderDetail.setTotalAmount(msOrderDetail.getProductQuantity() * msOrderDetail.getProductPrice());
            int i = msOrderDetailMapper.updateById(msOrderDetail);
            if (i < 1) {
                return ResultSet.fail("修改订单详情表失败");
            }
            if("0".equals(msOrderDetail.getIssueStatus())) {
                isDone = "0";
            }
        }

        //修改订单表
        MsOrder msOrder = MsProductMapping.INSTANCE.toMsOrder(update);
        if("1".equals(isDone)) {
            msOrder.setOrderStatus("2");
        }
        msOrder.setProductTotalPrice(update.getProductTotalPrice());
        int i = msOrderMapper.updateById(msOrder);
        if (i < 1) {
            return ResultSet.fail("修改订单表失败");
        }
        return ResultSet.success(null,"修改成功");

    }


}
