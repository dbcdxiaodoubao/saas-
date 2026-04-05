package com.mashang.ordering.domain.param.selete;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("租户套餐检索条件")
@Data
public class MsMealParam{

  @ApiModelProperty("套餐名称")
  private String mealName;

  @ApiModelProperty("状态")
  private String mealStatus;

  @ApiModelProperty("创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createTime;

}
