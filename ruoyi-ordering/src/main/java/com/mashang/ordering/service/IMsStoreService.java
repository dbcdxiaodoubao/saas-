package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.create.MsStoreCreate;
import com.mashang.ordering.domain.vo.MsStoreListVo;

import java.util.List;


public interface IMsStoreService extends IService<MsStore> {
    Page<MsStoreListVo> getMsStoreList(String storeName, String storeTel, PageQuery pageQuery);
    ResultSet<Object> addMsStore(MsStoreCreate msStoreCreate);
}
