package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsPrinter;
import com.mashang.ordering.domain.param.create.MsPrinterCreate;
import com.mashang.ordering.domain.param.update.MsPrinterUpdate;
import com.mashang.ordering.domain.vo.MsPrinterListVo;

import java.util.List;

public interface IMsPrinterService extends IService<MsPrinter> {
    ResultSet<Object> addMsPrinter(MsPrinterCreate msPrinterCreate);
    ResultSet<Object> updateMsPrinter(MsPrinterUpdate msPrinterUpdate);
    ResultSet<Object> deleteMsPrinter(long id);
}
