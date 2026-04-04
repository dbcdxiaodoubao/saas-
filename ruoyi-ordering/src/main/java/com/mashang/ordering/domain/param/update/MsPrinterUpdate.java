package com.mashang.ordering.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "MsPrinterUpdate",description = "打印机信息修改参数")
public class MsPrinterUpdate {
    @ApiModelProperty(value = "打印机ID", example = "1")
    @NotNull(message = "打印机ID不可为空")
    private Long printerId;

    @ApiModelProperty(value = "打印机名字", example = "测试打印机")
    @NotBlank(message = "打印机名字不可为空")
    private String printerName;

    @ApiModelProperty(value = "打印机设备码", example = "1234567890")
    @NotBlank(message = "打印机设备码不可为空")
    private String printerDeviceCode;
}
