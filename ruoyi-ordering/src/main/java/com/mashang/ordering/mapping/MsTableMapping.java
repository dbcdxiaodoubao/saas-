package com.mashang.ordering.mapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsTable;
import com.mashang.ordering.domain.param.create.MsMealCreate;
import com.mashang.ordering.domain.param.create.MsTableCreate;
import com.mashang.ordering.domain.param.update.MsTableUpdate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;
import com.mashang.ordering.domain.vo.MsMealListVo;
import com.mashang.ordering.domain.vo.MsTableDtlVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MsTableMapping {

    MsTableMapping INSTANCE = Mappers.getMapper(MsTableMapping.class);

    /*桌号添加转实体*/
    MsTable toCreate(MsTableCreate msTableCreate);

    /*桌号实体转详情*/
    MsTableDtlVo toDtl(MsTable msTable);

    /*桌号添加转实体*/
    MsTable toUpdate(MsTableUpdate msTableUpdate);
}
