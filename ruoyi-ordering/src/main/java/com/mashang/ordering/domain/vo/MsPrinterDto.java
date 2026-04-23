package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsPrinter",description = "打印机信息")
public class MsPrinterDto {

    @ApiModelProperty(value = "打印机ID", example = "1")
    private long printerId;

    @ApiModelProperty(value = "打印机名字", example = "测试打印机")
    private String printerName;

    @ApiModelProperty(value = "打印机设备码", example = "1234567890")
    private String printerDeviceCode;
}
