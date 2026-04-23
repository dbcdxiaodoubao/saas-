package com.mashang.ordering.controller.manage;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.param.create.MsTableBatchCreate;
import com.mashang.ordering.domain.param.create.MsTableCreate;
import com.mashang.ordering.domain.param.selete.MsTableOrderParam;
import com.mashang.ordering.domain.param.selete.MsTableParam;
import com.mashang.ordering.domain.vo.MsTableDtlVo;
import com.mashang.ordering.domain.param.update.MsTableUpdate;
import com.mashang.ordering.domain.vo.*;
import com.mashang.ordering.mapping.MsTableMapping;
import com.mashang.ordering.service.IMsOrderService;
import com.mashang.ordering.service.IMsTableService;
import com.mashang.ordering.utils.QrCodeUtil;
import com.mashang.ordering.utils.Transfromer;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.mashang.ordering.domain.common.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "管理端-桌号管理")
@RestController
@RequestMapping("/admin/table")
public class MsTableController extends BaseController {

    @Autowired
    private IMsTableService msTableService;

    @Autowired
    private IMsOrderService msOrderService;

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
        Page<MsTableListVo> page = msTableService.tablePage(Transfromer.oPage2rPage(pageQuery), msTableParam);
        return getDataTable(page.getRecords(), page.getTotal());

    }

    @ApiOperation("查询桌号信息详情")
    @ApiImplicitParam(name = "tableId", value = "桌号id")
    @GetMapping("/dtl/{tableId}")
    public R<MsTableDtlVo> selectById(@PathVariable Long tableId){

        LambdaQueryWrapper<MsTable> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsTable::getTableId, tableId);

        long count = msTableService.count(lqw);
        if (count == 0){
            return R.fail("当前餐桌不存在，请重新输入!");
        }
        return R.ok(msTableService.selectByTableId(tableId));
    }

    @ApiOperation("修改桌号信息")
    @PutMapping
    public R updateById(@RequestBody @Validated MsTableUpdate msTableUpdate){
        LambdaQueryWrapper<MsTable> lqw = new LambdaQueryWrapper<>();

        lqw.eq(MsTable::getTableNumber, StringUtils.trim(msTableUpdate.getTableNumber()));

        lqw.ne(MsTable::getTableId, msTableUpdate.getTableId());

        MsTable one = msTableService.getOne(lqw);

        if (StringUtils.isNotNull(one)){
            return R.fail("当前桌号已存在,请重新修改!");
        }

        return toResult(msTableService.updateById(MsTableMapping.INSTANCE.toUpdate(msTableUpdate)));
    }

    @ApiOperation("删除桌号信息")
    @ApiImplicitParam(name = "tableId", value = "桌号id")
    @DeleteMapping("/{tableId}")
    public R deleteById(@PathVariable Long tableId){
        return toResult(msTableService.removeById(tableId));
    }

    @ApiOperation(("查询所有餐桌名称列表"))
    @GetMapping("/tableNames")
    public R<List<MsStoreNameVo>> selectTeacherNames(){
        return R.ok(msTableService.selectStoreNames());
    }


    @ApiOperation("生成桌号二维码")
    @GetMapping("/qrcode/{tableId}")
    public void generateQrCode(@PathVariable Long tableId, HttpServletResponse response) throws IOException {
        // 1. 查询桌号
        MsTable table = msTableService.getById(tableId);

        // 2. 构建你要的 JSON 对象
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("tableId", table.getTableId());
        jsonMap.put("tableNumber", table.getTableNumber());

        // 3. 转成 JSON 字符串（这就是要存库的内容）
        String qrJson = JSON.toJSONString(jsonMap);

        // 4. 存入数据库 qr_code 字段
        table.setQrCode(qrJson);
        msTableService.updateById(table);

        // 5. 根据 JSON 生成二维码图片返回前端
        response.setContentType("image/png");
        QrCodeUtil.generateQRCodeByContent(qrJson, 300, response.getOutputStream());
    }

    @ApiOperation(value = "下载桌号二维码", produces = "image/png")
    @GetMapping("/qrcode/download/{tableId}")
    public void downloadQrCode(@PathVariable Long tableId, HttpServletResponse response) throws IOException {
        // 1. 查询桌号
        MsTable table = msTableService.getById(tableId);
        if (table == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "桌号不存在");
            return;
        }

        // 2. 拿数据库里的JSON内容
        String qrJson = table.getQrCode();
        if (qrJson == null || qrJson.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "请先生成二维码");
            return;
        }

        // 3. 文件名编码
        String fileName = table.getTableNumber() + "_桌码";
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

        // 4. 设置响应头
        response.setContentType("image/png");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".png");

        // 5. 输出二维码
        QrCodeUtil.generateQRCodeByContent(qrJson, 300, response.getOutputStream());
        response.getOutputStream().flush();
    }


    @ApiOperation("获取桌号相关订单")
    @GetMapping("/order/{tableId}")
    public TableDataInfo<List<MsTableOrderListVo>> getTableOrder(@Validated MsTableOrderParam msTableOrderParam,@Validated PageQuery pageQuery){
        ResultSet<Page<MsTableOrderListVo>> resultSet = msOrderService.getMsTableOrderListVo(msTableOrderParam, pageQuery);
        return getDataTable(resultSet.getData().getRecords(), resultSet.getData().getTotal());
    }

    @ApiOperation("获取桌号相关订单详情")
    @GetMapping("/order/dtl/{tableId},{orderId}")
    public R<MsTableOrderDto> getTableOrderDtl(@PathVariable Long orderId, @PathVariable Long tableId){
        if (orderId == null){
            return R.fail("订单编号不能为空");
        }
        if(tableId==null){
            return R.fail("桌号编号不能为空");
        }
        return R.ok(msOrderService.getMsTableOrderDto(orderId,tableId).getData());
    }
}
