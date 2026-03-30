package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class MsProduct {

  @TableId(type = IdType.AUTO)
  private long productId;
  private long productCategoriesId;
  private String productName;
  private String keyword;
  private String specificationType;
  private String unitName;
  private double productPrice;
  private double marketPrice;
  private String totalInventory;
  private String productCover;
  private String productCarousel;
  private String productIntroduction;
  private String status;
  private String earnPoints;
  private String createBy;
  //todo 这里看看是改java.utils.Date还是java.sql.Timestamp
  private Date createTime;
  private String updateBy;
  private Date updateTime;
  private String delFlag;

}
