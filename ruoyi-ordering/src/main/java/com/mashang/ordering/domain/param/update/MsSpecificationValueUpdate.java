package com.mashang.ordering.domain.param.update;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsSpecificationValueUpdate",description = "商品规格值更新类型")
public class MsSpecificationValueUpdate{
  @TableId
  @ApiModelProperty(value = "规格值ID")
  private Long specificationValueId;

  @ApiModelProperty(value = "规格类型值名")
  private String specs;

  @ApiModelProperty(value = "规格加价格")
  private Long attr;

}
