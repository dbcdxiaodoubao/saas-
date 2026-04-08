package com.mashang.ordering.domain.param.create;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Data
public class MsSpecificationCreate {

  @ApiModelProperty(value = "规格名称")
  @NotBlank(message = "规格名称不能为空")
  private String specificationName;

  @ApiModelProperty(value = "排序")
  @NotNull(message = "排序不能为空")
  private Long sort;

  /*因为这个录的是json值 所以我不知道示例怎么写……
  [{
    "type":"规格类型名字",
      "values":[{ //规格值列表
          "value":"单个规格值",
          "addPrice":0 //加价
        }
      ]
    }
  ]
  */
  @ApiModelProperty(value = "规格及属性值",example = "[{\"type\":\"规格类型名字\",\"values\":[\"value\":\"单个规格值\",\"addPrice\":0]}]")
  @NotNull(message = "规格及属性值不能为空")
  private List<HashMap<String, Object>> specsAndAttrs;


}
