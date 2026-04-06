package com.mashang.ordering.controller.manage;

import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageQuery;
import com.mashang.ordering.domain.vo.MsProductPageVo;
import com.mashang.ordering.mapper.MsProductMapper;
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

    @Autowired
    private MsProductMapper msProductMapper;

    @ApiOperation("模糊分页查询商品列表")
    @GetMapping("page")
    public TableDataInfo<List<MsProductPageVo>> page(MsProductPageQuery msProductPageQuery){
        return msProductService.selectProductPage(msProductPageQuery);
    }

    @ApiOperation("添加商品")
    @PostMapping("add")
    public R add(@RequestBody @Validated MsProductCreate msProductCreate){
        ResultSet resultSet = msProductService.addProduct(msProductCreate);
        if (resultSet.isSuccess()) {
            return R.ok(resultSet.getData(),"添加商品成功");
        }
        return R.fail("添加商品失败");
    }

    @ApiOperation("根据id查询商品详情")
    @PostMapping("{productId}")
    public R selectDtl(@PathVariable Long productId){
        MsProduct msProduct = msProductMapper.selectById(productId);
        if (msProduct != null) {
            return R.ok(msProduct,"查询商品详情成功");
        }
        return R.fail("查询商品详情失败");
    }


}
