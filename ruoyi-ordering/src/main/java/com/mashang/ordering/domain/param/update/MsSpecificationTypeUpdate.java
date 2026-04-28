package com.mashang.ordering.domain.param.update;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsSpecificationTypeUpdate",description = "商品规格类更新类型")
public class MsSpecificationTypeUpdate {

  @TableId
  @ApiModelProperty(value = "规格类型ID")
  private Long specificationTypeId;

  @ApiModelProperty(value = "规格类型名")
  private String specificationTypeName;

  @ApiModelProperty(value = "规格值")
  private List<MsSpecificationValueUpdate> specificationValues;

}
