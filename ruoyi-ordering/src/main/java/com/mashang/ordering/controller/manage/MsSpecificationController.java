package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.vo.MsSpecificationDto;
import com.mashang.ordering.domain.vo.MsSpecificationListVo;
import com.mashang.ordering.mapping.MsSpecificationMapping;
import com.mashang.ordering.service.IMsSpecificationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("获得规格列表")
    @GetMapping("/list")
    public TableDataInfo<List<MsSpecificationListVo>> list(String msSpecificationName, PageQuery pageQuery) {
        Page<MsSpecificationListVo> page = msSpecificationService.getSpecificationList(msSpecificationName,pageQuery);
        return getDataTable(page.getRecords());
    }

    @ApiOperation("获得规格详情")
    @GetMapping("/{msSpecificationId}")
    public R<MsSpecificationDto> getSpecification(@PathVariable("msSpecificationId") Long msSpecificationId) {
        MsSpecification po = msSpecificationService.getById(msSpecificationId);
        MsSpecificationDto dto = MsSpecificationMapping.INSTANCE.toDto(po);
        return R.ok(dto);
    }

    @ApiOperation("更新规格详情")
    @PutMapping("")
    public R updateSpecification(@Validated @RequestBody MsSpecificationUpdate msSpecificationUpdate) {
        ResultSet<Object> result = msSpecificationService.updateSpecification(msSpecificationUpdate);
        if (result.isSuccess()) {
            return R.ok(result.getData());
        }else{
            return R.fail(result.getMessage());
        }
    }

    @ApiOperation("删除规格详情")
    @DeleteMapping("/{id}")
    public R deleteSpecification(@PathVariable("id") Long id) {
        ResultSet<Object> result = msSpecificationService.deleteSpecification(id);
        if (result.isSuccess()) {
            return R.ok(result.getData());
        }else{
            return R.fail(result.getMessage());
        }
    }


}
