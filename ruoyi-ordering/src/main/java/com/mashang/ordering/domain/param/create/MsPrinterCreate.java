package com.mashang.ordering.domain.param.create;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "MsPrinterCreate",description = "打印机信息添加参数")
public class MsPrinterCreate {

    @ApiModelProperty(value = "打印机名字", example = "测试打印机")
    @NotBlank(message = "打印机名字不能为空,请填写此项")
    private String printerName;

    @ApiModelProperty(value = "打印机设备码", example = "1234567890")
    @NotBlank(message = "打印机设备码不能为空,请填写此项")
    private String printerDeviceCode;
}
