package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "MsStoreDto",description = "门店详情参数")
public class MsStoreDto {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "门店ID",example = "1")
    private long storeId;

    @ApiModelProperty(value = "门店名称",example = "测试门店")
    private String storeName;

    @ApiModelProperty(value = "门店电话",example = "172XXXXYYYY")
    private String storeTel;

    @ApiModelProperty(value = "门店头像",example = "/pi.jpg")
    private String storeAvatar;

    @ApiModelProperty(value = "门店组图",example = "/p1.jpg,p2.jpg,p3.jpg")
    private String storePhotoGallery;

    @ApiModelProperty(value = "门店开始营业时间",example = "09:00:00")
    private String businessStartTime;

    @ApiModelProperty(value = "门店结束营业时间",example = "22:00:00")
    private String businessEndTime;

    @ApiModelProperty(value = "门店所在行政区域",example = "北京海淀区")
    private String administrativeRegion;

    @ApiModelProperty(value = "门店经度",example = "116.367754")
    private String longitude;

    @ApiModelProperty(value = "门店纬度",example = "39.90923")
    private String latitude;

    @ApiModelProperty(value = "门店详细地址",example = "北京市海淀区")
    private String detailAddress;

    @ApiModelProperty(value = "门店公告",example = "测试公告")
    private String announcement;

    @ApiModelProperty(value = "门店是否开启",example = "1-开 0-关")
    private char isOpen;

    @ApiModelProperty(value = "门店所有者ID",example = "1")
    private long userId;

    @ApiModelProperty(value = "门店打印机ID",example = "1")
    private long printerId;
}
