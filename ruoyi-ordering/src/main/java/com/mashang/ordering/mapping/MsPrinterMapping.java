package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsPrinter;
import com.mashang.ordering.domain.param.create.MsPrinterCreate;
import com.mashang.ordering.domain.param.create.MsStoreCreate;
import com.mashang.ordering.domain.param.update.MsPrinterUpdate;
import com.mashang.ordering.domain.vo.MsPrinterDto;
import com.mashang.ordering.domain.vo.MsPrinterListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MsPrinterMapping {
    //从数据库实体转出为其他实体用to 用其他实体构造数据库实体用from
    MsPrinterMapping INSTANCE = Mappers.getMapper(MsPrinterMapping.class);

    MsPrinter fromCreate(MsPrinterCreate msPrinterCreate);

    MsPrinter fromUpdate(MsPrinterUpdate msPrinterUpdate);

    List<MsPrinterListVo> toVoList(List<MsPrinter> msPrinterList);

    MsPrinterDto toDto(MsPrinter msPrinter);
}
