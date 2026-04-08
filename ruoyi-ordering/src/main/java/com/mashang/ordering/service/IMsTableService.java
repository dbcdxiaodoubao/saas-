package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.param.create.MsTableBatchCreate;

public interface IMsTableService extends IService<MsTable> {

    int batchInsertTable(MsTableBatchCreate msTableBatchCreate);
}
