package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户订单列表商品实体")
public class MsUserOderProduct {

  @ApiModelProperty("商品id")
  private long productId;

  @ApiModelProperty("商品封面url")
  private String productCover;
}
