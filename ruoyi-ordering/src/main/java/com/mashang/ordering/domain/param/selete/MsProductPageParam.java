package com.mashang.ordering.domain.param.selete;

import com.ruoyi.common.core.page.PageDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品分页查询请求")
public class MsProductPageParam extends PageDomain {

    @ApiModelProperty("商品分类id")
    private Integer categoriesId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("店铺名称")
    private String storeName;

    @ApiModelProperty("商品状态（'0',下架，'1'上架，'2'已售罄）")
    private String status;

}
