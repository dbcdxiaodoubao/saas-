package com.mashang.ordering.controller;

import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.service.IMsOrderService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/home")
public class MsOrderController {

    @Autowired
    private IMsOrderService msOrderService;

    @ApiOperation("获取首页统计数据")
    @GetMapping("/getDate")
    public R<MsOrderDTO> getDate(){
        MsOrderDTO msOrderDate = msOrderService.getMsOrderDate();
        return R.ok(msOrderDate);
    }
}
