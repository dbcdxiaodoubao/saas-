package com.mashang.ordering.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.vo.MsUserProductDtlVo;
import com.mashang.ordering.domain.vo.MsUserProductListVo;
import com.mashang.ordering.service.IMsUserProductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-product")
@Api(tags = "用户订单管理")
public class MsUserProductController extends BaseController {

    @Autowired
    private IMsUserProductService msUserProductService;

    @GetMapping
    @ApiOperation("查询商品列表")
    public TableDataInfo<List<MsUserProductListVo>> list(@Validated PageQuery pageQuery
            ,@ApiParam(value = "搜索关键词", required = false)String keyWord
            ,@ApiParam(value = "分类id", required = false)Long productCategoriesId){

        Page<MsUserProductListVo> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<MsUserProductListVo> list = msUserProductService.getList(productCategoriesId,keyWord);

        return getDataTable(page.getResult(),page.getTotal());
    }

    @GetMapping("/dtl/{id}")
    @ApiOperation("查询商品详情")
    public R<MsUserProductDtlVo> dtl(@Validated @PathVariable Long id){

        return R.ok(msUserProductService.getDtl(id));
    }
}
