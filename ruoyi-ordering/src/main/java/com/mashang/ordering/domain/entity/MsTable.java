package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsTable",description = "餐桌信息")
public class MsTable extends BaseModel {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "餐桌ID", example = "1")
    private long tableId;

    @ApiModelProperty(value = "门店ID", example = "1")
    private long storeId;

    @ApiModelProperty(value = "桌号", example = "1")
    private String tableNumber;

    @ApiModelProperty(value = "桌号前缀", example = "TEST")
    private String tableNumberPrefix;

    @ApiModelProperty(value = "桌号起始值", example = "0")
    private long startTableNumber;

    @ApiModelProperty(value = "桌号终止值", example = "99")
    private long endTableNumber;

    @ApiModelProperty(value = "餐桌二维码", example = "/qrcode/1.png")
    private String qrCode;

    @ApiModelProperty(value = "餐桌状态", example = "1-启用 0-禁用")
    private char state;                           // 1-启用 0-禁用
}
