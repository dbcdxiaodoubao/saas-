package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.param.create.MsUserOrderProductCreate;
import com.mashang.ordering.domain.param.selete.MsUserOrderPageParam;
import com.mashang.ordering.domain.vo.MsUserOrderListPageVo;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;
import com.mashang.ordering.domain.vo.MsUserTableListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    /**
     * 创建新订单
     * @param create
     */
    void insertOrder(MsUserOrderCreate create);

    /**
     * 为订单绑定商品
     * @param orderId
     * @param list
     */
    void batchInsertOrderDetail(
            @Param("orderId") Long orderId,
            @Param("list") List<MsUserOrderProductCreate> list
    );

    /**
     * 查询加菜次数
     * @param orderId
     * @return
     */
    Long selectAddCount(Long orderId);

    /**
     * 用户加菜
     * @param orderId
     * @param list
     */
    void addProduct(
            @Param("orderId") Long orderId,
            @Param("list") List<MsUserOrderProductCreate> list
    );

    /**
     * 支付
     * @param orderId
     */
    void pay(@Param("orderId")Long orderId,
             @Param("totalAmount")String totalAmount);

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
     * 根据订单号查询订单id
     * @param orderNumber
     * @return
     */
    Long getOrderIdByOrderNumberLong(String orderNumber);

    /**
     * 通过订单id查询桌号
     * @param orderId
     * @return
     */
    String getLabelNumber(Long orderId);

    /**
     * 用户退款
     * @param money
     * @param userId
     */
    void userRecharge(@Param("userId") Long userId,@Param("money") BigDecimal money);

    /**
     * 查询订单
     * @param msUserOrderPageParam
     * @return
     */
    List<MsUserOrderListPageVo> selectOrderPage(MsUserOrderPageParam msUserOrderPageParam);
}
