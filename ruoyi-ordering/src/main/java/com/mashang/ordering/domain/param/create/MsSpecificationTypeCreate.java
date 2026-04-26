package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "MsSpecificationTypeCreate",description = "商品规格类类型添加参数")
public class MsSpecificationTypeCreate {
    @ApiModelProperty(value = "规格类型名")
    @NotBlank(message = "规格类型名不能为空")
    private String specificationTypeName;

    @ApiModelProperty(value = "规格值表")
    @NotNull(message = "规格值表不能为空")
    private List<MsSpecificationValueCreate> specificationValues;
}
