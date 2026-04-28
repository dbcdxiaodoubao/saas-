package com.mashang.ordering.domain.param.update;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.entity.MsSpecificationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "MsSpecificationUpdate",description = "商品规格更新参数")
public class MsSpecificationUpdate {

  @ApiModelProperty(value = "规格ID")
  @NotNull(message = "规格id不能为空")
  private Long specificationId;

  @ApiModelProperty(value = "规格名称")
  @NotBlank(message = "规格名称不能为空")
  private String specificationName;

  @ApiModelProperty(value = "排序")
  @NotNull(message = "排序不能为空")
  private Long sort;

  @ApiModelProperty(value = "规格及属性值")
  @NotNull(message = "规格及属性值不能为空")
  private List<MsSpecificationTypeUpdate> specsAndAttrs;


}
