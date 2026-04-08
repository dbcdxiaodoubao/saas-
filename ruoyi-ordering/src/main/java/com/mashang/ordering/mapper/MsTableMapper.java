package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.vo.MsMealListVo;
import com.mashang.ordering.domain.vo.MsTableListVo;
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

}
