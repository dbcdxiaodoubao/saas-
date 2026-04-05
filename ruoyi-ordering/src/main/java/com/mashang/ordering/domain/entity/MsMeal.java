package com.mashang.ordering.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import lombok.Data;

@Data
public class MsMeal extends BaseModel {

  @TableId(type = IdType.AUTO)
  private long mealId;
  private String mealName;
  private String mealStatus;

}
