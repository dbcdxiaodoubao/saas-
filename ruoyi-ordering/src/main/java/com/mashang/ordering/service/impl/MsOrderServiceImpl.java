package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsOrderDetail;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.selete.MsTableOrderParam;
import com.mashang.ordering.domain.param.selete.MsOrderPageParam;
import com.mashang.ordering.domain.param.update.MsUserOrderDetailUpdate;
import com.mashang.ordering.domain.param.update.MsUserOrderUpdate;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.domain.vo.MsOrderListPageVo;
import com.mashang.ordering.domain.vo.MsTableOrderDto;
import com.mashang.ordering.domain.vo.MsTableOrderListVo;
import com.mashang.ordering.mapper.*;
import com.mashang.ordering.mapping.MsProductMapping;
import com.mashang.ordering.service.IMsOrderService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MsOrderServiceImpl extends ServiceImpl<MsOrderMapper, MsOrder> implements IMsOrderService{


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired MsOrderMapper msOrderMapper;

    @Autowired
    private MsProductMapper msProductMapper;

    @Autowired
    private MsStoreMapper msStoreMapper;

    @Autowired
    private MsOrderDetailMapper msOrderDetailMapper;

    @Override
    public MsOrderDTO getMsOrderDate() {
        MsOrderDTO dto = new MsOrderDTO();

        /**
         * 会员总数
         */
        Long memberTotal = sysUserMapper.selectCount(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getDelFlag, 0)
        );
        dto.setMemberTotal(memberTotal);

        /**
         * 订单总数
          */
        Long orderTotal = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setOrderTotal(orderTotal);

        /**
         * 商品总数
         */
        Long productTotal = msProductMapper.selectCount(
                Wrappers.lambdaQuery(MsProduct.class)
                        .eq(MsProduct::getDelFlag, 0)
        );
        dto.setProductTotal(productTotal);

        /**
         * 总金额
         */
        BigDecimal amountTotal = msOrderMapper.getTotalAmount();
        dto.setAmountTotal(amountTotal == null ? BigDecimal.ZERO : amountTotal);

        /**
         * 今日订单
         */
        LocalDateTime today = LocalDate.now().atStartOfDay();
        Long todayOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .ge(MsOrder::getCreateTime, today)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setTodayOrder(todayOrder);

        /**
         * 昨日订单
         */
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atStartOfDay();
        Long yesterdayOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .between(MsOrder::getCreateTime, yesterday, today)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setYesterdayOrder(yesterdayOrder);

        /**
         * 昨日订单
         */
        LocalDateTime last7Day = LocalDate.now().minusDays(7).atStartOfDay();
        Long last7DaysOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .ge(MsOrder::getCreateTime, last7Day)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setLast7DaysOrder(last7DaysOrder);

        /**
         * 本月
         */
        LocalDateTime monthStart = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        Long thisMonthOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .ge(MsOrder::getCreateTime, monthStart)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setThisMonthOrder(thisMonthOrder);

        return dto;
    }

    @Override
    public ResultSet<Page<MsTableOrderListVo>> getMsTableOrderListVo(MsTableOrderParam msTableOrderParam, PageQuery pageQuery) {
        QueryWrapper<Object> qw = Wrappers.query();
        qw.eq("t3.table_id", msTableOrderParam.getTableId());
        //todo 问清楚订单状态枚举值
        String orderStatus = msTableOrderParam.getOrderStatus();
        if(orderStatus != null){
            if(orderStatus.length() != 1) {
                return ResultSet.fail("订单状态参数错误");
            }
        }
        String payType = msTableOrderParam.getPayType();
        if(payType != null){
            if(!"0".equals(payType) && !"1".equals(payType)) {
                return ResultSet.fail("支付方式参数错误");}
        }
        qw.eq("t3.table_id", msTableOrderParam.getTableId());
        qw.eq(msTableOrderParam.getOrderStatus()!=null,"t1.order_status", msTableOrderParam.getOrderStatus());
        qw.eq(msTableOrderParam.getPayType()!=null,"t1.pay_type", msTableOrderParam.getPayType());
        qw.like(msTableOrderParam.getOrderNumber()!=null,"t1.order_number", msTableOrderParam.getOrderNumber());
        qw.like(msTableOrderParam.getUserName()!=null,"t5.user_name", msTableOrderParam.getUserName());
        qw.like(msTableOrderParam.getUserTel()!=null,"t5.user_tel", msTableOrderParam.getUserTel());
        List<MsTableOrderListVo> list = msOrderMapper.getMsTableOrderListVo(qw);
        Page<MsTableOrderListVo> page = page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), list.size());
        page.setRecords(list);
        return ResultSet.success(page);
    }

    @Override
    public ResultSet<MsTableOrderDto> getMsTableOrderDto(Long orderId,Long tableId) {
        QueryWrapper<Object> qw = Wrappers.query();
        qw.eq("t1.order_id", orderId);
        qw.eq("t3.table_id", tableId);
        return ResultSet.success(msOrderMapper.getMsTableOrderDto(qw));
    }

    @Override
    public TableDataInfo<List<MsOrderListPageVo>> selectOrderPage(MsOrderPageParam msOrderPageParam) {

        //根据店主的商店数来返回的订单
        LoginUser loginUser = SecurityUtils.getLoginUser();
        LambdaQueryWrapper<MsStore> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MsStore::getUserId, loginUser.getUserId());
        wrapper.eq(MsStore::getDelFlag, 0);
        List<MsStore> msStores = msStoreMapper.selectList(wrapper);
        msOrderPageParam.setStoreIds(msStores.stream().map(MsStore::getStoreId).collect(Collectors.toList()));

        PageHelper.startPage(msOrderPageParam.getPageNum(), msOrderPageParam.getPageSize());

        List<MsOrderListPageVo> msUserOrderListPageVos = msOrderMapper.selectOrderPage(msOrderPageParam);
        TableDataInfo<List<MsOrderListPageVo>> tableDataInfo = new TableDataInfo<>();
        tableDataInfo.setTotal(msUserOrderListPageVos.size());
        tableDataInfo.setRows(msUserOrderListPageVos);

        if(!msUserOrderListPageVos.isEmpty()){
            tableDataInfo.setCode(200);
            tableDataInfo.setMsg("查询成功");
            return tableDataInfo;
        }

        tableDataInfo.setCode(200);
        tableDataInfo.setMsg("无订单");

        return tableDataInfo;

    }

    @Override
    @Transactional
    public ResultSet updateOrderDtl(MsUserOrderUpdate update) {

        List<MsUserOrderDetailUpdate> userOderProductDtlList = update.getMsUserOrderDetailUpdates();

        //修改订单详情表
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
        //如果商品全是已出单，修改订单状态为2已完成
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
