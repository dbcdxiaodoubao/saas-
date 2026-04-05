package com.mashang.ordering.mapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.param.create.MsMealCreate;
import com.mashang.ordering.domain.param.create.MsStoreCreate;
import com.mashang.ordering.domain.vo.MsMealDtlVo;
import com.mashang.ordering.domain.vo.MsMealListVo;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MsMealMapping {

    MsMealMapping INSTANCE = Mappers.getMapper(MsMealMapping.class);

    /*套餐添加转实体*/
    MsMeal toCreate(MsMealCreate msMealCreate);

    /*将租户套餐实体转列表分页*/
    Page<MsMealListVo> toPage(Page<MsMeal> msMealPage);

    /*将租户套餐实体转详情*/
    MsMealDtlVo toDtl(MsMeal msMeal);
}
