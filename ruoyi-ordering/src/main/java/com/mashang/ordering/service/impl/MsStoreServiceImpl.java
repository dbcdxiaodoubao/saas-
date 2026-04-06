package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsPrinter;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.create.MsPrinterCreate;
import com.mashang.ordering.domain.param.create.MsStoreCreate;
import com.mashang.ordering.domain.param.update.MsStoreUpdate;
import com.mashang.ordering.domain.vo.MsPrinterListVo;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import com.mashang.ordering.mapper.MsPrinterMapper;
import com.mashang.ordering.mapper.MsStoreMapper;
import com.mashang.ordering.mapping.MsPrinterMapping;
import com.mashang.ordering.mapping.MsStoreMapping;
import com.mashang.ordering.service.IMsStoreService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mashang.ordering.utils.Checker;

import java.util.Collections;
import java.util.List;

@Service
public class MsStoreServiceImpl extends ServiceImpl<MsStoreMapper, MsStore> implements IMsStoreService {

    @Autowired
    private MsStoreMapper msStoreMapper;

    @Override
    public Page<MsStoreListVo> getMsStoreList(String storeName, String storeTel, PageQuery pageQuery) {
        LambdaQueryWrapper<MsStore> lqw = new LambdaQueryWrapper<MsStore>();
        lqw.like(StringUtils.isNotEmpty(storeName), MsStore::getStoreName, storeName);
        lqw.like(StringUtils.isNotEmpty(storeTel), MsStore::getStoreTel, storeTel);
        List<MsStoreListVo> list = MsStoreMapping.INSTANCE.toListVos(msStoreMapper.getMsStoreList(lqw));
        Page<MsStoreListVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), list.size());
        page.setRecords(list);
        return page;
    }

    @Override
    public ResultSet<Object> addMsStore(MsStoreCreate msStoreCreate) {
        boolean right = true;
        String msg = "";
        if(msStoreCreate.getIsOpen().length() > 1 || (msStoreCreate.getIsOpen().charAt(0)!= '1' && msStoreCreate.getIsOpen().charAt(0)!= '0')){
            right = false;
            msg += "门店营业状态输入有误 1-开 0-关-";
        }
        if(!Checker.isTimeString(msStoreCreate.getBusinessStartTime()) || !Checker.isTimeString(msStoreCreate.getBusinessEndTime())){
            right = false;
            msg += "门店营业时间输入有误-";
        }
        LambdaQueryWrapper<MsStore> lqw = new LambdaQueryWrapper<MsStore>();
        lqw.eq(MsStore::getStoreName, msStoreCreate.getStoreName());
        if(msStoreMapper.selectOne(lqw) != null){
            right = false;
            msg += "当前门店名称已存在-";
        }
        if(!right){
            return ResultSet.fail(msg.substring(0, msg.length() - 1));
        }
        MsStore msStore = MsStoreMapping.INSTANCE.fromCreate(msStoreCreate);
        return ResultSet.success(msStoreMapper.insert(msStore));
    }

    @Override
    public ResultSet<Object> updateMsStore(MsStoreUpdate msStoreUpdate) {
        boolean right = true;
        String msg = "";
        if(msStoreUpdate.getIsOpen().length() > 1 || (msStoreUpdate.getIsOpen().charAt(0)!= '1' && msStoreUpdate.getIsOpen().charAt(0)!= '0')){
            right = false;
            msg += "门店营业状态输入有误 1-开 0-关-";
        }
        if(!Checker.isTimeString(msStoreUpdate.getBusinessStartTime()) || !Checker.isTimeString(msStoreUpdate.getBusinessEndTime())){
            right = false;
            msg += "门店营业时间输入有误-";
        }
        LambdaQueryWrapper<MsStore> lqw = new LambdaQueryWrapper<MsStore>();
        lqw.eq(MsStore::getStoreName, msStoreUpdate.getStoreName());
        if(msStoreMapper.selectOne(lqw) != null){
            right = false;
            msg += "当前门店名称已存在-";
        }
        if(!right){
            return ResultSet.fail(msg.substring(0, msg.length() - 1));
        }
        MsStore msStore = MsStoreMapping.INSTANCE.fromUpdate(msStoreUpdate);
        return ResultSet.success(msStoreMapper.updateById(msStore));
    }

    @Override
    public ResultSet<Object> deleteMsStore(Long id) {
        //todo 检查桌号
        return ResultSet.success(msStoreMapper.deleteById(id));
    }
}
