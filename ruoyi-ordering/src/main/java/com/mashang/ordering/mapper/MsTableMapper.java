package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsTableMapper extends BaseMapper<MsTable> {

    /**
     *批量插入桌号信息
     * @param list
     * @return
     */
    int batchInsert(List<MsTable> list);

    /**
     * 分页查询餐桌信息列表
     * @param page
     * @param wrapper
     * @return
     */
    Page<MsTableListVo> tablePage(@Param("page")Page<MsTableListVo> page,
                                  @Param(Constants.WRAPPER) Wrapper<MsTableListVo> wrapper);

    /**
     * 查询所有店铺名称列表
     * @return
     */

    List<MsStoreNameVo> selectStoreNames();


    /**
     * 查询餐桌详情
     * @param tableId
     * @return
     */
    MsTableDtlVo selectByTableId(Long tableId);
}
