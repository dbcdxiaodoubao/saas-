package com.mashang.ordering.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.service.IMsOrderService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.mapper.SysNoticeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/home")
@Api(tags = "管理端-主页")
public class MsOrderController {

    @Autowired
    private IMsOrderService msOrderService;

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    @ApiOperation("获取首页统计数据")
    @GetMapping("/getDate")
    public R<MsOrderDTO> getDate(){
        MsOrderDTO msOrderDate = msOrderService.getMsOrderDate();
        return R.ok(msOrderDate);
    }


    @ApiOperation("获取首页最新公告列表")
    @GetMapping("/homeList")
    public R<List<SysNotice>> homeList() {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                        SysNotice::getNoticeId,
                        SysNotice::getNoticeTitle,
                        SysNotice::getNoticeContent,
                        SysNotice::getNoticeType,
                        SysNotice::getStatus,
                        SysNotice::getCreateTime,
                        SysNotice::getRemark
                )
                .eq(SysNotice::getStatus, "0")  // 正常公告
                .orderByDesc(SysNotice::getCreateTime)
                .last("LIMIT 5");

        List<SysNotice> list = sysNoticeMapper.selectList(wrapper);
        return R.ok(list);
    }
}
