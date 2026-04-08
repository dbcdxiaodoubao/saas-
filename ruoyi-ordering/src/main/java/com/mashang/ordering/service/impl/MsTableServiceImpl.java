package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.param.create.MsTableBatchCreate;
import com.mashang.ordering.domain.param.selete.MsTableParam;
import com.mashang.ordering.domain.vo.MsMealListVo;
import com.mashang.ordering.domain.vo.MsStoreNameVo;
import com.mashang.ordering.domain.vo.MsTableListVo;
import com.mashang.ordering.mapper.MsTableMapper;
import com.mashang.ordering.service.IMsTableService;
import com.ruoyi.common.core.page.PageQuery;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MsTableServiceImpl extends ServiceImpl<MsTableMapper, MsTable> implements IMsTableService {

    @Autowired
    private MsTableMapper msTableMapper;

    @Override
    public int batchInsertTable(MsTableBatchCreate msTableBatchCreate) {

        // 1. 安全获取起止编号（避免空指针）
        Integer start = msTableBatchCreate.getStartTableNumber();
        Integer end = msTableBatchCreate.getEndTableNumber();

        // 额外校验：起始不能大于结束
        if (start > end) {
            throw new RuntimeException("桌号起始值不能大于结束值！");
        }

        // 2. 生成所有要插入的桌号
        List<String> tableNumberList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            String fullNumber = msTableBatchCreate.getTableNumberPrefix() + i;
            tableNumberList.add(fullNumber);
        }

        // 3. 判断桌号是否已存在
        LambdaQueryWrapper<MsTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MsTable::getStoreId, msTableBatchCreate.getStoreId());
        queryWrapper.in(MsTable::getTableNumber, tableNumberList);

        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("当前店铺内部分桌号已存在，请重新输入！");
        }

        // 4. 组装批量插入数据
        List<MsTable> saveList = new ArrayList<>();
        for (String num : tableNumberList) {
            MsTable table = new MsTable();
            table.setStoreId(msTableBatchCreate.getStoreId());
            table.setTableNumber(num);
            table.setTableNumberPrefix(msTableBatchCreate.getTableNumberPrefix());
            table.setStartTableNumber(msTableBatchCreate.getStartTableNumber());
            table.setEndTableNumber(msTableBatchCreate.getEndTableNumber());

            table.setState(msTableBatchCreate.getState());
            table.setRemark(msTableBatchCreate.getRemark());
            table.setDelFlag("0");
            saveList.add(table);
        }
        // 5. 批量保存
        return msTableMapper.batchInsert(saveList);
    }

    @Override
    public Page<MsTableListVo> tablePage(PageQuery pageQuery, MsTableParam msTableParam) {

        Page<MsTableListVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());

        QueryWrapper<MsTableListVo> qw = new QueryWrapper();

        qw.eq(StringUtils.isNotEmpty(msTableParam.getStoreName()),
                "t2.store_name", msTableParam.getStoreName());
        qw.eq(StringUtils.isNotEmpty(msTableParam.getTableNumber()),
                "t1.table_number", msTableParam.getTableNumber());
        qw.eq("t1.del_flag", 0);
        return msTableMapper.tablePage(page, qw);
    }

    @Override
    public List<MsStoreNameVo> selectStoreNames() {
        return msTableMapper.selectStoreNames();
    }

}
