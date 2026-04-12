package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.create.MsUserOrderAdd;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.param.selete.MsUserOrderPageParam;
import com.mashang.ordering.domain.vo.MsUserOrderListPageVo;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;
import com.mashang.ordering.domain.vo.MsUserTableListVo;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    /**
     * 用户加菜
     * @param msUserOrderAdd
     */
    void addProduct(MsUserOrderAdd msUserOrderAdd);

    /**
     * 支付
     * @param orderId
     */
    void pay(Long orderId,String totalAmount);

    /**
     * 退款
     * @param orderId
     */
    void updateOrderStatusToRefund(Long orderId);

    /**
     * 查询该商店的桌号id
     * @param storeId
     * @return
     */
    List<MsUserTableListVo> getLabelId(Long storeId);

    /**
     * 用户退款
     * @param money
     * @param orderId
     */
    void userRecharge(@Param("orderId") Long orderId, @Param("money") BigDecimal money);


    TableDataInfo<List<MsUserOrderListPageVo>> selectOrderPage(MsUserOrderPageParam msUserOrderPageParam);
}
