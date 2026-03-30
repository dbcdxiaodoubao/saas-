package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import com.mashang.ordering.mapper.MsStoreMapper;
import com.mashang.ordering.mapping.MsStoreMapping;
import com.mashang.ordering.service.IMsStoreService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MsStoreServiceImpl extends ServiceImpl<MsStoreMapper, MsStore> implements IMsStoreService {

    @Autowired
    private MsStoreMapper msStoreMapper;

    @Override
    public List<MsStoreListVo> getMsStoreList(String storeName, String storeTel, Page<MsStore> page) {
        LambdaQueryWrapper<MsStore> lqw = new LambdaQueryWrapper<MsStore>();
        lqw.like(StringUtils.isNotEmpty(storeName), MsStore::getStoreName, storeName);
        lqw.like(StringUtils.isNotEmpty(storeTel), MsStore::getStoreTel, storeTel);
        return MsStoreMapping.INSTANCE.toListVos(msStoreMapper.getMsStoreList(page, lqw));
    }
}
