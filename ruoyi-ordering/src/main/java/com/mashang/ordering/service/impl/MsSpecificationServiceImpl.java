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

    public ResultSet<Object> addSpecification(String specificationStr) {
        HashMap<String,Object> specificationObj = JSON.parseObject(specificationStr,
                new TypeReference<HashMap<String, Object>>() {}.getType());
        if(specificationObj.get("specificationName")!=null){
            try{
                String specificationName = (String) specificationObj.get("specificationName");
                if(specificationName==null || specificationName.equals("")){
                    return ResultSet.fail("规格名称不能为空");
                }
            }catch (Exception e){
                return ResultSet.fail("规格名称格式错误");
            }
        }
        else{
            return ResultSet.fail("规格名称不能为空");
        }
        if(specificationObj.get("sort")!=null){
            try{
                Long sort = (Long) specificationObj.get("sort");
                if(sort==null){
                    return ResultSet.fail("排序不能为空");
                }
            }catch (Exception e){
                return ResultSet.fail("排序格式错误");
            }
        }else{
            return ResultSet.fail("排序不能为空");
        }
        if(specificationObj.get("specsAndAttrs")!=null){
            try{
                String specsAndAttrs = (String) specificationObj.get("specsAndAttrs");
                if(specsAndAttrs==null || specsAndAttrs.equals("")){
                    return ResultSet.fail("规格及属性值不能为空");
                }
                if(!Checker.isValidJson(specsAndAttrs)){
                    return ResultSet.fail("规格及属性值格式错误");
                }
            }catch (Exception e){
                return ResultSet.fail("规格及属性值格式错误");
            }
        }else{
            return ResultSet.fail("规格及属性值不能为空");
        }
        MsSpecification msSpecification = new MsSpecification();
        msSpecification.setSpecificationName((String) specificationObj.get("specificationName"));
        msSpecification.setSort((Long) specificationObj.get("sort"));
        msSpecification.setSpecsAndAttrs((String) specificationObj.get("specsAndAttrs"));
        int insert = msSpecificationMapper.insert(msSpecification);
        if(insert!=1){
            return ResultSet.fail("添加规格失败");
        }
        return ResultSet.success(msSpecification.getSpecificationId(),"添加规格成功");
    }

    @Override
    public ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate) {
        MsSpecification msSpecification = new MsSpecification();
        msSpecification.setSpecificationName(msSpecificationCreate.getSpecificationName());
        msSpecification.setSort(msSpecificationCreate.getSort());
        msSpecification.setSpecsAndAttrs(JSON.toJSONString(msSpecificationCreate.getSpecsAndAttrs(), JSONWriter.Feature.WriteNulls));
        int insert = msSpecificationMapper.insert(msSpecification);
        if(insert!=1){
            return ResultSet.fail("添加规格失败");
        }
        return ResultSet.success(msSpecification.getSpecificationId(),"添加规格成功");
    }
}