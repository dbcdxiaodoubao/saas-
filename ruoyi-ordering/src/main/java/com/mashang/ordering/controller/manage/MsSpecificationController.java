package com.mashang.ordering.controller.manage;

import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.service.IMsSpecificationService;
import com.ruoyi.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理端-规格管理")
@RestController
@RequestMapping("/specification")
public class MsSpecificationController extends BaseController {

    @Autowired
    private IMsSpecificationService msSpecificationService;

    @ApiOperation("添加规格")
    @PostMapping("/")
    public R addSpecification(@Validated @RequestBody MsSpecificationCreate msSpecificationCreate) {
        ResultSet<Object> result = msSpecificationService.addSpecification(msSpecificationCreate);
        if (result.isSuccess()) {
            return R.ok(result.getData());
        }else{
            return R.fail(result.getMessage());
        }
    }
}
