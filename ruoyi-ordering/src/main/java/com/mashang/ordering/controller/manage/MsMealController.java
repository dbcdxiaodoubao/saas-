package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsMealMenu;
import com.mashang.ordering.domain.param.create.MsMealCreate;
import com.mashang.ordering.domain.param.selete.MsMealParam;
import com.mashang.ordering.domain.param.update.MsMealUpdate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;
import com.mashang.ordering.domain.vo.MsMealListVo;
import com.mashang.ordering.mapping.MsMealMapping;
import com.mashang.ordering.service.IMsMealMenuService;
import com.mashang.ordering.service.IMsMealService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.page.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "管理端-租户套餐管理")
@RestController
@RequestMapping("/admin/meal")
public class MsMealController extends BaseController {

    @Autowired
    private IMsMealService msMealService;


    @ApiOperation(("新增租户套餐信息"))
    @PostMapping
    public R insert(@RequestBody @Validated MsMealCreate msMealCreate) {

        LambdaQueryWrapper<MsMeal> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsMeal::getMealName, StringUtils.trim(msMealCreate.getMealName()));
        //检验班级名称是否重复
        MsMeal one = msMealService.getOne(lqw);
        if (StringUtils.isNotNull(one)){
            return R.fail("当前套餐名已存在,请重新添加!");
        }

        return toResult(msMealService.insertMeal(msMealCreate));
    }

    @ApiOperation(("分页查询租户套餐列表"))
    @GetMapping("/list")
    public TableDataInfo<List<MsMealListVo>> list(@Validated PageQuery pageQuery, MsMealParam msMealParam) {

        LambdaQueryWrapper<MsMeal> lqw = new LambdaQueryWrapper();
        lqw.like(StringUtils.isNotEmpty(msMealParam.getMealName()), MsMeal::getMealName, msMealParam.getMealName());

        lqw.eq(StringUtils.isNotEmpty(msMealParam.getMealStatus()), MsMeal::getMealStatus, msMealParam.getMealStatus());

        lqw.orderByDesc(MsMeal::getCreateTime);

        Page<MsMeal> page = msMealService.page(new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize()), lqw);

        Page<MsMealListVo> result = MsMealMapping.INSTANCE.toPage(page);

        return getDataTable(result.getRecords(), result.getTotal());
    }

    @ApiOperation("查询租户套餐信息详情")
    @ApiImplicitParam(name = "mealId", value = "租户套餐id")
    @GetMapping("/dtl/{mealId}")
    public R<MsMealDtlVo> selectById(@PathVariable Long mealId) {
        return R.ok(msMealService.getMealDtl(mealId));
    }

    @ApiOperation(("修改租户套餐信息"))
    @PutMapping
    public R updateById(@RequestBody @Validated MsMealUpdate msMealUpdate) {

        //容错
        LambdaQueryWrapper<MsMeal> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsMeal::getMealName, StringUtils.trim(msMealUpdate.getMealName()));
        //检验班级名称是否重复
        MsMeal one = msMealService.getOne(lqw);
        if (StringUtils.isNotNull(one)){
            return R.fail("当前租户套餐已存在,请重新添加!");
        }

        return toResult(msMealService.updateMeal(msMealUpdate));
    }

    @ApiOperation("删除租户套餐信息")
    @DeleteMapping("/{mealId}")
    @ApiImplicitParam(name = "mealId", value = "租户套餐id")
    public R deleteById(@PathVariable Long mealId){

        return toResult(msMealService.deleteMeal(mealId));
    }

    @ApiOperation("获取套餐树")
    @GetMapping("/menuTree")
    public R getMenuTree() {
        return R.ok(msMealService.getMenuTree());
    }
}
