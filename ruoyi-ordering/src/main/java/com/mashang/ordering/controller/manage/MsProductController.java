package com.mashang.ordering.controller.manage;

import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageParam;
import com.mashang.ordering.domain.param.update.MsProductUpdate;
import com.mashang.ordering.domain.vo.MsProductDtlVo;
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
@Api(tags = "管理端-商品管理")
public class MsProductController {

    @Autowired
    private IMsProductService msProductService;

    @ApiOperation("模糊分页查询商品列表")
    @GetMapping("page")
    public TableDataInfo<List<MsProductPageVo>> page(MsProductPageParam msProductPageParam){
        return msProductService.selectProductPage(msProductPageParam);
    }

    @ApiOperation("添加商品")
    @PostMapping("add")
    public R add(@RequestBody @Validated MsProductCreate msProductCreate) throws Exception {
        ResultSet resultSet = msProductService.addProduct(msProductCreate);
        if (resultSet.isSuccess()) {
            return R.ok(resultSet.getData(),"添加商品成功");
        }
        return R.fail(resultSet.getMessage());
    }

    @ApiOperation("根据id查询商品详情")
    @GetMapping("selectDtl/{productId}")
    public R selectDtl(@PathVariable Long productId){

        MsProductDtlVo msProductDtlVo = msProductService.selectProductDtl(productId);

        if (msProductDtlVo != null) {
            return R.ok(msProductDtlVo,"查询商品详情成功");
        }
        return R.fail("查询商品详情失败");
    }

    @ApiOperation("修改商品")
    @PutMapping("update")
    public R update(@RequestBody @Validated MsProductUpdate msProductUpdate){

        ResultSet resultSet = msProductService.updateProduct(msProductUpdate);

        if (resultSet.isSuccess()) {
            return R.ok(resultSet.getData(),"修改商品成功");
        }
        return R.fail(resultSet.getMessage());
    }

    @ApiOperation("删除商品")
    @DeleteMapping("delete/{productId}")
    public R delete(@PathVariable Long productId){

        ResultSet resultSet = msProductService.deleteProduct(productId);

        if (resultSet.isSuccess()) {
            return R.ok(resultSet.getData(),"删除商品成功");
        }
        return R.fail(resultSet.getMessage());
    }


}
