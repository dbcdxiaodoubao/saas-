package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("店铺名称实体")
@Data
public class MsStoreNameVo {

    @ApiModelProperty("店铺ID")
    private Long storeId;

    @ApiModelProperty("店铺名称")
    private String storeName;
}
