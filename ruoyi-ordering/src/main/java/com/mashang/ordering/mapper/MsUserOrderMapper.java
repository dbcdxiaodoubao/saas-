package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.param.create.MsUserOrderProductCreate;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
