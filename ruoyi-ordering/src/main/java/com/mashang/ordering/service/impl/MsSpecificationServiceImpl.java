package com.mashang.ordering.service.impl;

import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;
import com.mashang.ordering.domain.vo.MsSpecificationListVo;
import com.mashang.ordering.mapper.MsProductMapper;
import com.mashang.ordering.mapper.MsSpecificationMapper;
import com.mashang.ordering.mapping.MsSpecificationMapping;
import com.mashang.ordering.service.IMsSpecificationService;
import com.mashang.ordering.utils.Checker;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.util.HashMap;
import java.util.List;

@Service
public class MsSpecificationServiceImpl extends ServiceImpl<MsSpecificationMapper, MsSpecification>
        implements IMsSpecificationService {

    @Autowired
    private MsSpecificationMapper msSpecificationMapper;

    @Autowired
    private MsProductMapper msProductMapper;

    @Override
    public ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate) {
        MsSpecification msSpecification = MsSpecificationMapping.INSTANCE.fromCreate(msSpecificationCreate);
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsSpecification::getSpecificationName, msSpecification.getSpecificationName());
        if(msSpecificationMapper.selectOne(lqw) != null){
            return ResultSet.fail("规格名重复");
        }
        int insert = msSpecificationMapper.insert(msSpecification);
        if(insert!=1){
            return ResultSet.fail("添加规格失败");
        }
        return ResultSet.success(msSpecification.getSpecificationId(),"添加规格成功");
    }

    @Override
    public Page<MsSpecificationListVo> getSpecificationList(String name, PageQuery pageQuery) {
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(name), MsSpecification::getSpecificationName, name);
        List<MsSpecificationListVo> list = MsSpecificationMapping.INSTANCE.toListVos(msSpecificationMapper.selectList(lqw));
        Page<MsSpecificationListVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), list.size());
        page.setRecords(list);
        return page;
    }

    @Override
    public ResultSet<Object> updateSpecification(MsSpecificationUpdate msSpecificationUpdate) {
        MsSpecification msSpecification = MsSpecificationMapping.INSTANCE.fromUpdate(msSpecificationUpdate);
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsSpecification::getSpecificationName, msSpecification.getSpecificationName());
        if(msSpecificationMapper.selectOne(lqw) != null){
            return ResultSet.fail("规格名重复");
        }
        int update = msSpecificationMapper.updateById(msSpecification);
        if(update!=1){
            return ResultSet.fail("修改失败");
        }
        return ResultSet.success(null);
    }

    @Override
    public ResultSet<Object> deleteSpecification(Long id) {
        LambdaQueryWrapper<MsProduct> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsProduct::getSpecificationId, id);
        if(msProductMapper.selectOne(lqw) != null){
            return ResultSet.fail("当前规格值正被商品所使用");
        }
        int delete = msSpecificationMapper.deleteById(id);
        if(delete!=1){
            return ResultSet.fail("删除失败");
        }
        return ResultSet.success(null);
    }
}