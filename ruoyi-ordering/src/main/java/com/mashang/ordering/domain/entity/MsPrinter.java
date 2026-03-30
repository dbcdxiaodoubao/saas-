package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MsPrinter extends BaseModel {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "打印机ID", example = "1")
    private long printerId;

    @ApiModelProperty(value = "打印机名字", example = "测试打印机")
    private String printerName;

    @ApiModelProperty(value = "打印机设备码", example = "1234567890")
    private String printerDeviceCode;
}
