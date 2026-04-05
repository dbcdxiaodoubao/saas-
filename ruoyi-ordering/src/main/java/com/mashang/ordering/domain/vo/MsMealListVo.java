package com.mashang.ordering.domain.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("分页查询套餐信息列表")
@Data
public class MsMealListVo{

  @ApiModelProperty("套餐编号")
  private long mealId;

  @ApiModelProperty("套餐名称")
  private String mealName;

  @ApiModelProperty("状态")
  private String mealStatus;

  @ApiModelProperty("创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createTime;
}
