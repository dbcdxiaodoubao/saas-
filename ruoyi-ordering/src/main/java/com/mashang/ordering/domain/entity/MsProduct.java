package com.mashang.ordering.domain.entity;


public class MsProduct {

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
  private java.sql.Timestamp createTime;
  private String updateBy;
  private java.sql.Timestamp updateTime;
  private String delFlag;


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getProductCategoriesId() {
    return productCategoriesId;
  }

  public void setProductCategoriesId(long productCategoriesId) {
    this.productCategoriesId = productCategoriesId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }


  public String getSpecificationType() {
    return specificationType;
  }

  public void setSpecificationType(String specificationType) {
    this.specificationType = specificationType;
  }


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }


  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }


  public double getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(double marketPrice) {
    this.marketPrice = marketPrice;
  }


  public String getTotalInventory() {
    return totalInventory;
  }

  public void setTotalInventory(String totalInventory) {
    this.totalInventory = totalInventory;
  }


  public String getProductCover() {
    return productCover;
  }

  public void setProductCover(String productCover) {
    this.productCover = productCover;
  }


  public String getProductCarousel() {
    return productCarousel;
  }

  public void setProductCarousel(String productCarousel) {
    this.productCarousel = productCarousel;
  }


  public String getProductIntroduction() {
    return productIntroduction;
  }

  public void setProductIntroduction(String productIntroduction) {
    this.productIntroduction = productIntroduction;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getEarnPoints() {
    return earnPoints;
  }

  public void setEarnPoints(String earnPoints) {
    this.earnPoints = earnPoints;
  }


  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
