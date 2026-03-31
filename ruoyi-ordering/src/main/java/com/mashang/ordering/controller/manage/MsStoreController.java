package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import com.ruoyi.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashang.ordering.service.IMsStoreService;

import java.util.List;

@RestController
@RequestMapping("/store")
@Api(tags = "门店管理")
public class MsStoreController extends BaseController {

    @Autowired
    private IMsStoreService msStoreService;

    //todo 在参数文件夹搞定之后再说……
    //todo 在有了 TableDataInfo 或者 TableAjaxResult 再说……
    @ApiOperation("获取门店列表")
    @GetMapping("/list")
    private List<MsStoreListVo> getMsStoreList() {
        return msStoreService.getMsStoreList("", "", new Page<MsStore>(10, 10));
    }

}
