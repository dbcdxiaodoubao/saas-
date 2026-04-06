package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MsStoreProduct {

  @ApiModelProperty(value = "门店商品ID")
  @TableId(type = IdType.AUTO)
  private Long storeProductId;

  @ApiModelProperty(value = "商品ID")
  private Long productId;

  @ApiModelProperty(value = "门店ID")
  private Long storeId;


}
