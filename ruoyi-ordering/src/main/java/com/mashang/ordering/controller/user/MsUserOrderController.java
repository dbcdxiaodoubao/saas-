package com.mashang.ordering.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.param.create.MsUserOrderAdd;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.vo.*;
import com.mashang.ordering.service.IMsUserOrderService;
import com.mashang.ordering.service.impl.MsUserOrderServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-oder")
@Api(tags = "用户端-订单管理")
public class MsUserOrderController extends BaseController {
    //TODO 删除buytype字段
    @Autowired
    private IMsUserOrderService msUserOrderService;
    @Autowired
    private MsUserOrderServiceImpl msUserOrderServiceImpl;


    @GetMapping
    @ApiOperation("用户查询订单列表")
    public TableDataInfo<MsUserOrderListVo> list(@Validated PageQuery pageQuery
            , @ApiParam(value = "用户id", required = true)Long userId){
        Page<MsUserOrderListVo> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<MsUserOrderListVo> list = msUserOrderService.getList(userId);

        return getDataTable(page.getResult(),page.getTotal());
    }

    @GetMapping("/dtl/{orderId}")
    @ApiOperation("用户查询订单详情")
    public R<MsUserOrderDtlVo> dtl(@Validated @PathVariable long orderId){
        return R.ok(msUserOrderService.getDtl(orderId));
    }

    @PostMapping("/new")
    @ApiOperation("用户新建订单")
    public R newOder(@Validated @RequestBody MsUserOrderCreate msUserOrderCreate){

        List<MsUserTableListVo> tables = msUserOrderService.getLabelId(msUserOrderCreate.getStoreId());

        boolean flag = false;
        for (MsUserTableListVo table : tables) {
            if (table.getTableId()==msUserOrderCreate.getTableId()){
                flag = true;
            }
        }
        if (!flag){
            return R.fail("该桌号不存在于该商店中");
        }

        msUserOrderService.insertOrder(msUserOrderCreate);
        return R.ok();
    }

    @PostMapping("/add")
    @ApiOperation("用户加菜")
    public R add(@Validated @RequestBody MsUserOrderAdd msUserOrderAdd){

        if (msUserOrderService.getDtl(msUserOrderAdd.getOrderId())==null){
            return R.fail("该订单号不存在");
        }

        msUserOrderService.addProduct(msUserOrderAdd);
        return R.ok();
    }

}
