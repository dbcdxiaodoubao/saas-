package com.mashang.ordering.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import lombok.Data;

@Data
public class MsMealMenu extends BaseModel {

  @TableId(type = IdType.AUTO)
  private long mealMenuId;
  private long mealId;
  private long menuId;

}
