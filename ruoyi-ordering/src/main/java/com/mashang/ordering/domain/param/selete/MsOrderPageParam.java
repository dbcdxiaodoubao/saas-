package com.mashang.ordering.domain.param.selete;

import com.ruoyi.common.core.page.PageDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class MsOrderPageParam extends PageDomain {

    @ApiModelProperty(value = "购买类型（0堂食,1自取,2预约）")
    private String buyType;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "支付方式（0余额支付，1支付宝支付）")
    private String payType;

    @ApiModelProperty(value = "订单号")
    private String orderNumber;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户电话")
    private String phonenumber;

    @ApiModelProperty(value = "开始时间，空时传null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "结束时间,空时传null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "门店id列表，无需传",hidden = true)
    private List<Long> storeIds;

}
