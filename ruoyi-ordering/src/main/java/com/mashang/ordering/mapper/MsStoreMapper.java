package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsStoreMapper extends BaseMapper<MsStore> {

    List<MsStore> getMsStoreList(Page<MsStore> page, @Param("ew") Wrapper<MsStore> wrapper);
}
