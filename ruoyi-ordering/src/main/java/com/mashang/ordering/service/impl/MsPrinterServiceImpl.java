package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsPrinter;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.create.MsPrinterCreate;
import com.mashang.ordering.domain.param.update.MsPrinterUpdate;
import com.mashang.ordering.mapper.MsPrinterMapper;
import com.mashang.ordering.mapper.MsStoreMapper;
import com.mashang.ordering.mapping.MsPrinterMapping;
import com.mashang.ordering.mapping.MsStoreMapping;
import com.mashang.ordering.service.IMsPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsPrinterServiceImpl extends ServiceImpl<MsPrinterMapper, MsPrinter> implements IMsPrinterService {
    @Autowired
    private MsPrinterMapper msPrinterMapper;

    @Autowired
    private MsStoreMapper msStoreMapper;

    @Override
    public ResultSet<Object> addMsPrinter(MsPrinterCreate msPrinterCreate) {
        LambdaQueryWrapper<MsPrinter> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsPrinter::getPrinterName, msPrinterCreate.getPrinterName());
        if(msPrinterMapper.selectOne(lqw) != null){
            return ResultSet.fail("打印机设备名不可重复");
        }
        lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsPrinter::getPrinterDeviceCode,msPrinterCreate.getPrinterDeviceCode());
        if(msPrinterMapper.selectOne(lqw) != null){
            return ResultSet.fail("打印机设备码不可重复");
        }
        MsPrinter msPrinter = MsPrinterMapping.INSTANCE.fromCreate(msPrinterCreate);
        return ResultSet.success(msPrinterMapper.insert(msPrinter));
    }

    @Override
    public ResultSet<Object> updateMsPrinter(MsPrinterUpdate msPrinterUpdate) {
        LambdaQueryWrapper<MsPrinter> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsPrinter::getPrinterName, msPrinterUpdate.getPrinterName());
        if(msPrinterMapper.selectOne(lqw) != null){
            return ResultSet.fail("打印机设备名不可重复");
        }
        lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsPrinter::getPrinterDeviceCode,msPrinterUpdate.getPrinterDeviceCode());
        if(msPrinterMapper.selectOne(lqw) != null){
            return ResultSet.fail("打印机设备码不可重复");
        }
        MsPrinter msPrinter = MsPrinterMapping.INSTANCE.fromUpdate(msPrinterUpdate);
        return ResultSet.success(msPrinterMapper.updateById(msPrinter));
    }

    @Override
    public ResultSet<Object> deleteMsPrinter(long id) {
        LambdaQueryWrapper<MsStore> storeLqw = new LambdaQueryWrapper<>();
        storeLqw.eq(MsStore::getStoreId, id);
        if(msStoreMapper.selectOne(storeLqw) != null){
            return ResultSet.fail("绑定了店铺的打印机无法删除");
        }
        return ResultSet.success(msPrinterMapper.deleteById(id));
    }
}
