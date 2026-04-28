package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.vo.MsSpecificationDto;
import com.mashang.ordering.domain.vo.MsSpecificationVo;
import com.mashang.ordering.mapping.MsSpecificationMapping;
import com.mashang.ordering.service.IMsSpecificationService;
import com.mashang.ordering.service.impl.MsSpecificationServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import com.ruoyi.common.core.domain.R;
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
        ResultSet<Object> result = null;
        try {
            result = msSpecificationService.addSpecification(msSpecificationCreate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (result.isSuccess()) {
            return R.ok(result.getData());
        }else{
            return R.fail(result.getMessage());
        }
    }

    @ApiOperation("获得规格列表")
    @GetMapping("/list")
    public TableDataInfo<List<MsSpecificationVo>> list(String msSpecificationName, PageQuery pageQuery) {
        Page<MsSpecificationVo> page = msSpecificationService.getSpecificationList(msSpecificationName,pageQuery);
        return getDataTable(page.getRecords());
    }

    @ApiOperation("获得规格详情")
    @GetMapping("/{msSpecificationId}")
    public R<MsSpecificationDto> getSpecification(@PathVariable Long msSpecificationId) {
        ResultSet<MsSpecificationVo> resultSet = msSpecificationService.getSpecificationById(msSpecificationId);
        if (!resultSet.isSuccess() || resultSet.getData() == null) {
            return R.fail(resultSet.getMessage());
        }
        MsSpecificationDto msSpecificationDto = MsSpecificationServiceImpl.voToDto(resultSet.getData());
        return R.ok(msSpecificationDto);
    }

    @ApiOperation("更新规格详情")
    @PutMapping("")
    public R updateSpecification(@Validated @RequestBody MsSpecificationUpdate msSpecificationUpdate) {
        try{
            ResultSet<Object> result = msSpecificationService.updateSpecification(msSpecificationUpdate);
            if (result.isSuccess()) {
                return R.ok();
            }
        }catch (Exception e){
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @ApiOperation("删除规格详情")
    @DeleteMapping("/{msSpecificationId}")
    public R deleteSpecification(@PathVariable Long msSpecificationId) {
        try{
            ResultSet<Object> result = msSpecificationService.deleteSpecification(msSpecificationId);
            if (result.isSuccess()) {
                return R.ok();
            }
        }catch (Exception e){
            return R.fail(e.getMessage());
        }
        return R.fail();
    }


}
