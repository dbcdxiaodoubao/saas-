package com.mashang.ordering.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("门店创建参数")
public class MsStoreUpdate {
    @ApiModelProperty(value = "门店ID",example = "1")
    @NotNull(message = "门店ID不能为空,此项为必填项")
    private Long storeId;

    @ApiModelProperty(value = "门店名称",example = "测试门店")
    @NotBlank(message = "门店名称不能为空,此项为必填项")
    private String storeName;

    @ApiModelProperty(value = "门店电话",example = "172XXXXYYYY")
    @NotBlank(message = "门店电话不能为空,此项为必填项")
    private String storeTel;

    @ApiModelProperty(value = "门店头像",example = "/pi.jpg")
    @NotBlank(message = "门店头像不能为空,此项为必填项")
    private String storeAvatar;

    @ApiModelProperty(value = "门店组图",example = "/p1.jpg,p2.jpg,p3.jpg")
    @NotBlank(message = "门店组图不能为空,此项为必填项")
    private String storePhotoGallery;

    @ApiModelProperty(value = "门店开始营业时间",example = "09:00:00")
    @NotBlank(message = "门店开始营业时间不能为空,此项为必填项")
    private String businessStartTime;

    @ApiModelProperty(value = "门店结束营业时间",example = "22:00:00")
    @NotBlank(message = "门店结束营业时间不能为空,此项为必填项")
    private String businessEndTime;

    @ApiModelProperty(value = "门店所在行政区域",example = "北京海淀区")
    @NotBlank(message = "门店所在行政区域不能为空,此项为必填项")
    private String administrativeRegion;

    @ApiModelProperty(value = "门店经度",example = "116.367754")
    @NotBlank(message = "门店经度不能为空,此项为必填项")
    private String longitude;

    @ApiModelProperty(value = "门店纬度",example = "39.90923")
    @NotBlank(message = "门店纬度不能为空,此项为必填项")
    private String latitude;

    @ApiModelProperty(value = "门店详细地址",example = "北京市海淀区")
    @NotBlank(message = "门店详细地址不能为空,此项为必填项")
    private String detailAddress;

    @ApiModelProperty(value = "门店公告",example = "测试公告")
    @NotBlank(message = "门店公告不能为空,此项为必填项")
    private String announcement;

    @ApiModelProperty(value = "门店是否营业",example = "1-开 0-关")
    @NotBlank(message = "营业状态不能为空")
    private String isOpen;

    @ApiModelProperty(value = "门店打印机ID",example = "1")
    @NotNull(message = "门店打印机ID不能为空,此项为必填项")
    private Long printerId;
}
