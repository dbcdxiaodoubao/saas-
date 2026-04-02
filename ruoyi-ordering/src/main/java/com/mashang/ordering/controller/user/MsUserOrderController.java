package com.mashang.ordering.controller.user;

import com.mashang.ordering.service.IMsUserOrderService;
import com.ruoyi.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-oder")
@Api(tags = "用户端订单管理")
public class MsUserOrderController extends BaseController {

    @Autowired
    IMsUserOrderService msUserOrderService;



}
