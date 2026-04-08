package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.param.create.MsTableBatchCreate;
import com.mashang.ordering.domain.param.create.MsTableCreate;
import com.mashang.ordering.domain.param.selete.MsTableParam;
import com.mashang.ordering.domain.vo.MsTableListVo;
import com.mashang.ordering.mapping.MsTableMapping;
import com.mashang.ordering.service.IMsTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "管理端-桌号管理")
@RestController
@RequestMapping("/admin/table")
public class MsTableController extends BaseController {

    @Autowired
    private IMsTableService msTableService;

    @ApiOperation("新增桌号信息")
    @PostMapping("/insert")
    public R insertTable(@RequestBody @Validated MsTableCreate msTableCreate){
        LambdaQueryWrapper<MsTable> lqw = new LambdaQueryWrapper<>();

        lqw.eq(MsTable::getTableNumber, msTableCreate.getTableNumber());

        long count = msTableService.count(lqw);
        if (count > 0){
           return R.fail("当前桌号已存在,请重新添加");
        }
        return toResult(msTableService.save(MsTableMapping.INSTANCE.toCreate(msTableCreate)));
    }

    @ApiOperation("批量新增桌号信息")
    @PostMapping("/batch/insert")
    public R insertTable(@RequestBody @Validated MsTableBatchCreate msTableBatchCreate){

        return toResult(msTableService.batchInsertTable(msTableBatchCreate));
    }

    @ApiOperation(("分页查询桌号信息列表"))
    @GetMapping("/list")
    public TableDataInfo<List<MsTableListVo>> list(@Validated PageQuery pageQuery, MsTableParam msTableParam){
        Page<MsTableListVo> page = msTableService.tablePage(pageQuery, msTableParam);
        return getDataTable(page.getRecords(), page.getTotal());

    }
}
