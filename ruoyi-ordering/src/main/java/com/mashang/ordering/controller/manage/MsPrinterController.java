package com.mashang.ordering.controller.manage;

import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsPrinter;
import com.mashang.ordering.domain.param.create.MsPrinterCreate;
import com.mashang.ordering.domain.param.update.MsPrinterUpdate;
import com.mashang.ordering.domain.vo.MsPrinterDto;
import com.mashang.ordering.domain.vo.MsPrinterListVo;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import com.mashang.ordering.mapping.MsPrinterMapping;
import com.mashang.ordering.mapping.MsStoreMapping;
import com.mashang.ordering.service.IMsPrinterService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/printer")
@Api(tags = "管理端-打印机管理")
public class MsPrinterController extends BaseController {

    @Autowired
    private IMsPrinterService printerService;

    @ApiOperation("获得所有打印机")
    @GetMapping("/list")
    public TableDataInfo<List<MsPrinterListVo>> list() {
        List<MsPrinterListVo> listVos = MsPrinterMapping.INSTANCE.toVoList(printerService.list());
        return getDataTable(listVos);
    }

    @ApiOperation("添加打印机")
    @PostMapping("")
    public R add(@RequestBody @Validated MsPrinterCreate msPrinterCreate) {
        ResultSet<Object> result = printerService.addMsPrinter(msPrinterCreate);
        if (result.isSuccess()) {
            return R.ok();
        }else{
            return R.fail(result.getMessage());
        }
    }

    @ApiOperation("获得打印机详情")
    @GetMapping("/{printId}")
    @ApiImplicitParam(name = "printId", value = "打印机id", required = true, dataType = "Long", paramType = "path")
    public R<MsPrinterDto> findById(@PathVariable("printId") long printId) {
        MsPrinter msPrinter = printerService.getById(printId);
        if(msPrinter == null){
            return R.fail("打印机不存在");
        }
        MsPrinterDto msPrinterDto = MsPrinterMapping.INSTANCE.toDto(msPrinter);
        return R.ok(msPrinterDto);
    }

    @ApiOperation("更新打印机")
    @PutMapping("")
    public R update(@RequestBody @Validated MsPrinterUpdate msPrinterUpdate) {
        ResultSet<Object> result = printerService.updateMsPrinter(msPrinterUpdate);
        if (result.isSuccess()) {
            return R.ok();
        }else{
            return R.fail(result.getMessage());
        }
    }

    @ApiOperation("删除打印机")
    @DeleteMapping("/{printerId}")
    @ApiImplicitParam(name = "printerId",value = "打印机id",required = true)
    public R delete(@PathVariable long printerId) {
        ResultSet<Object> result = printerService.deleteMsPrinter(printerId);
        if (result.isSuccess()) {
            return R.ok();
        }else{
            return R.fail(result.getMessage());
        }
    }

}
