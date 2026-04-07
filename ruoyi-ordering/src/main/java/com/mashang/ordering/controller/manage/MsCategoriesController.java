package com.mashang.ordering.controller.manage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.param.create.MsCategoriesCreate;
import com.mashang.ordering.domain.param.selete.MsCategoriesParam;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;
import com.mashang.ordering.service.IMsCategoriesService;
import com.ruoyi.common.core.domain.R;
import com.mashang.ordering.domain.common.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;

import java.util.List;

@RestController
@RequestMapping("/Categories")
@Api(tags = "管理端-商品分类管理")
public class MsCategoriesController extends BaseController {

    @Autowired
    private IMsCategoriesService msCategoriesService;

    @PostMapping("")
    public R addCategoriesWithStore(@Validated @RequestBody MsCategoriesCreate msCategoriesCreate) {
        try {
            ResultSet<Object> resultSet = msCategoriesService.addCategoriesWithStore(msCategoriesCreate);
            if (resultSet.isSuccess()) {
                return R.ok(resultSet.getData());
            } else {
                return R.fail(resultSet.getMessage());
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @GetMapping("/list")
    public TableDataInfo<List<MsCategoriesListVo>> list(MsCategoriesParam msCategoriesParam, PageQuery pageQuery) {
        Page<MsCategoriesListVo> page = msCategoriesService.getCategoriesList(msCategoriesParam, pageQuery);
        return new TableDataInfo<>(page.getRecords(), page.getTotal());
    }
}
