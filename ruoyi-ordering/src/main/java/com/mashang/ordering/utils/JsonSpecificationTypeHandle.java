package com.mashang.ordering.utils;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.mashang.ordering.domain.vo.MsSpecificationTypeVo;
import com.mashang.ordering.domain.vo.MsSpecificationValueVo;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@MappedTypes({List.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonSpecificationTypeHandle extends AbstractJsonTypeHandler<List<MsSpecificationTypeVo>> {

    @Override
    protected List<MsSpecificationTypeVo> parse(String json) {
        List<MsSpecificationTypeVo> typeVos = new ArrayList<>();
        List<LinkedHashMap<String,Object>> typeMaps = JSON.parseObject(json, List.class);
        for (LinkedHashMap<String,Object> typeMap : typeMaps) {
            MsSpecificationTypeVo typeVo = new MsSpecificationTypeVo();
            try {
                long specificationTypeId = 0;
                specificationTypeId = (Integer) typeMap.get("specificationTypeId");
                typeVo.setSpecificationTypeId(specificationTypeId);
            } catch (Exception e) {
                typeVo.setSpecificationTypeId(null);
            }
            typeVo.setSpecificationTypeName((String) typeMap.get("specificationTypeName"));
            List<MsSpecificationValueVo> valueVos = new ArrayList<>();
            List<LinkedHashMap<String,Object>> valueMaps = (List<LinkedHashMap<String, Object>>) typeMap.get("specificationValues");
            for (LinkedHashMap<String,Object> valueMap : valueMaps) {
                MsSpecificationValueVo valueVo = new MsSpecificationValueVo();
                try {
                    long valueId = (Integer) valueMap.get("specificationValueId");
                    valueVo.setSpecificationValueId(valueId);
                } catch (Exception e) {
                    valueVo.setSpecificationValueId(null);
                }
                valueVo.setSpecs((String) valueMap.get("specs"));
                try {
                    long attr = (Integer) valueMap.get("attr");
                    valueVo.setAttr(attr);
                } catch (Exception e) {
                    valueVo.setAttr(null);
                }
                valueVos.add(valueVo);
            }
            typeVo.setSpecificationValues(valueVos);
            typeVos.add(typeVo);
        }
        return typeVos;
    }

    @Override
    protected String toJson(List<MsSpecificationTypeVo> obj) {
        return JSON.toJSONString(obj);
    }
}
