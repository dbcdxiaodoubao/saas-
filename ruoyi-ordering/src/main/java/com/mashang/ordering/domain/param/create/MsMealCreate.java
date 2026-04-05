package com.mashang.ordering.domain.param.create;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("套餐添加实体")
@Data
public class MsMealCreate{

  @ApiModelProperty("菜单ID列表")
  @NotNull(message ="菜单ID列表不能为空")
  private List<Long> menuIds;

  @ApiModelProperty("套餐名称")
  @NotBlank(message = "套餐名称不能为空")
  private String mealName;

  @ApiModelProperty("套餐状态")
  @NotBlank(message = "套餐状态不能为空")
  private String mealStatus;

}
