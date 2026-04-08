package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsTable;

import java.util.List;

public interface MsTableMapper extends BaseMapper<MsTable> {

    // 批量插入
    int batchInsert(List<MsTable> list);
}
