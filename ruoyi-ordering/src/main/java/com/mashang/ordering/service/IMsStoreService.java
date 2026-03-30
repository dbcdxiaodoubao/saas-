package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.vo.MsStoreListVo;

import java.util.List;


public interface IMsStoreService extends IService<MsStore> {
    List<MsStoreListVo> getMsStoreList(String storeName, String storeTel, Page<MsStore> page);
}
