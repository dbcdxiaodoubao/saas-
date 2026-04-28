package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.create.MsStoreCreate;
import com.mashang.ordering.domain.param.selete.MsStoreListParam;
import com.mashang.ordering.domain.param.update.MsStoreUpdate;
import com.mashang.ordering.domain.vo.MsStoreDto;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import com.mashang.ordering.mapping.MsStoreMapping;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mashang.ordering.service.IMsStoreService;

import java.util.List;

@RestController
@RequestMapping("/store")
@Api(tags = "管理端-门店管理")
public class MsStoreController extends BaseController {

    @Autowired
    private IMsStoreService msStoreService;

    @ApiOperation("获取门店列表")
    @GetMapping("/list")
    private TableDataInfo<List<MsStoreListVo>> getMsStoreList(MsStoreListParam msStoreListParam,@Validated PageQuery pageQuery) {
        Page<MsStoreListVo> msStoreListVos = msStoreService.getMsStoreList(msStoreListParam.getStoreName(), msStoreListParam.getStoreTel(),pageQuery);
        return getDataTable(msStoreListVos.getRecords(), msStoreListVos.getTotal());
    }

    //4.2 因为用户和打印机外键问题 这里的插入暂时无法测试
    @ApiOperation("添加门店")
    @PostMapping("")
    private R addMsStore(@RequestBody @Validated MsStoreCreate msStoreCreate) {
        ResultSet<Object> objectResultSet = msStoreService.addMsStore(msStoreCreate);
        if(objectResultSet.isSuccess()){
            return R.ok(objectResultSet.getData());
        }
        else{
            return R.fail(objectResultSet.getMessage());
        }
    }

    @ApiOperation("获得门店详情")
    @GetMapping("/{storeId}")
    @ApiImplicitParam(name = "storeId", value = "门店id", required = true, dataType = "Long", paramType = "path")
    private R getMsStore(@PathVariable Long storeId) {
        MsStore msStore = msStoreService.getById(storeId);
        if(msStore == null){
            return R.fail("门店不存在");
        }
        MsStoreDto data = MsStoreMapping.INSTANCE.toDto(msStore);
        return R.ok(data);
    }

    @ApiOperation("修改门店")
    @PutMapping("")
    private R updateMsStore(@RequestBody @Validated MsStoreUpdate msStoreUpdate) {
        ResultSet<Object> objectResultSet = msStoreService.updateMsStore(msStoreUpdate);
        if(objectResultSet.isSuccess()){
            return R.ok(objectResultSet.getData());
        }
        else{
            return R.fail(objectResultSet.getMessage());
        }
    }

    @ApiOperation("删除门店")
    @DeleteMapping("/{storeId}")
    @ApiImplicitParam(name = "storeId", value = "门店id", required = true, dataType = "Long", paramType = "path")
    private R deleteMsStore(@PathVariable Long storeId) {
        ResultSet<Object> objectResultSet = msStoreService.deleteMsStore(storeId);
        if(objectResultSet.isSuccess()){
            return R.ok(objectResultSet.getData());
        }
        else{
            return R.fail(objectResultSet.getMessage());
        }
    }
}
