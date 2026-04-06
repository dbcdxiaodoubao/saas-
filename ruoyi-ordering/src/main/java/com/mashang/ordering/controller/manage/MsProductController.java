package com.mashang.ordering.controller.manage;

import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageQuery;
import com.mashang.ordering.domain.vo.MsProductPageVo;
import com.mashang.ordering.service.IMsProductService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Api(tags = "商品管理")
public class MsProductController {

    @Autowired
    private IMsProductService msProductService;

    @ApiOperation("模糊分页查询商品列表")
    @GetMapping("page")
    public TableDataInfo<List<MsProductPageVo>> page(MsProductPageQuery msProductPageQuery){
        return msProductService.selectProductPage(msProductPageQuery);
    }

    @ApiOperation("添加商品")
    @PostMapping("add")
    public R add(@RequestBody @Validated MsProductCreate msProductCreate){
        Integer result = msProductService.addProduct(msProductCreate);
        if (result > 0) {
            return R.ok(result,"添加商品成功");
        }
        return R.fail("添加商品失败");
    }


}
