package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.param.create.MsTableBatchCreate;
import com.mashang.ordering.domain.param.selete.MsTableParam;
import com.mashang.ordering.domain.vo.MsMealListVo;
import com.mashang.ordering.domain.vo.MsTableListVo;
import com.ruoyi.common.core.page.PageQuery;

import java.util.List;

public interface IMsTableService extends IService<MsTable> {


    /**
     *批量插入桌号信息
     * @param msTableBatchCreate
     * @return
     */
    int batchInsertTable(MsTableBatchCreate msTableBatchCreate);

    /**
     * 分页查询餐桌信息列表
     * @param msTableParam
     * @param pageQuery
     * @return
     */
    Page<MsTableListVo> tablePage(PageQuery pageQuery, MsTableParam msTableParam);
}
