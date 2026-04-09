package com.mashang.ordering.service.impl;

import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.mapper.MsSpecificationMapper;
import com.mashang.ordering.service.IMsSpecificationService;
import com.mashang.ordering.utils.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.util.HashMap;

@Service
public class MsSpecificationServiceImpl extends ServiceImpl<MsSpecificationMapper, MsSpecification>
        implements IMsSpecificationService {

    @Autowired
    private MsSpecificationMapper msSpecificationMapper;

    @Override
    public ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate) {
        MsSpecification msSpecification = new MsSpecification();
        msSpecification.setSpecificationName(msSpecificationCreate.getSpecificationName());
        msSpecification.setSort(msSpecificationCreate.getSort());
        msSpecification.setSpecsAndAttrs(msSpecificationCreate.getSpecsAndAttrs());
        int insert = msSpecificationMapper.insert(msSpecification);
        if(insert!=1){
            return ResultSet.fail("添加规格失败");
        }
        return ResultSet.success(msSpecification.getSpecificationId(),"添加规格成功");
    }
}