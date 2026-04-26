package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.entity.MsSpecificationType;
import com.mashang.ordering.domain.entity.MsSpecificationValue;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.domain.param.create.MsSpecificationTypeCreate;
import com.mashang.ordering.domain.param.create.MsSpecificationValueCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.vo.MsSpecificationDto;
import com.mashang.ordering.domain.vo.MsSpecificationVo;
import com.mashang.ordering.mapper.MsProductMapper;
import com.mashang.ordering.mapper.MsSpecificationMapper;
import com.mashang.ordering.mapper.MsSpecificationTypeMapper;
import com.mashang.ordering.mapper.MsSpecificationValueMapper;
import com.mashang.ordering.mapping.MsSpecificationMapping;
import com.mashang.ordering.mapping.MsSpecificationTypeMapping;
import com.mashang.ordering.mapping.MsSpecificationValueMapping;
import com.mashang.ordering.service.IMsSpecificationService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MsSpecificationServiceImpl extends ServiceImpl<MsSpecificationMapper, MsSpecification>
        implements IMsSpecificationService {

    public static MsSpecificationDto voToDto(MsSpecificationVo msSpecificationVo) {
        MsSpecificationDto msSpecificationDto = MsSpecificationMapping.INSTANCE.voToDto(msSpecificationVo);
        msSpecificationDto.setSpecsAndAttrs(msSpecificationVo.getSpecsAndAttrs());
        return msSpecificationDto;
    }

    @Autowired
    private MsSpecificationMapper msSpecificationMapper;

    @Autowired
    private MsSpecificationTypeMapper msSpecificationTypeMapper;

    @Autowired
    private MsSpecificationValueMapper msSpecificationValueMapper;

    @Autowired
    private MsProductMapper msProductMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate) throws Exception {
        //添加规格(组)
        MsSpecification msSpecification = MsSpecificationMapping.INSTANCE.fromCreate(msSpecificationCreate);
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsSpecification::getSpecificationName, msSpecification.getSpecificationName());
        if(msSpecificationMapper.selectOne(lqw) != null){
            return ResultSet.fail("规格名重复");
        }
        int insert0 = msSpecificationMapper.insert(msSpecification);
        if (insert0 == 0) {
            return ResultSet.fail("添加规格失败");
        }
        //添加规格类
        List<MsSpecificationTypeCreate> specsAndAttrs = msSpecificationCreate.getSpecsAndAttrs();
        List<MsSpecificationType> msSpecificationTypes = MsSpecificationTypeMapping.INSTANCE.fromCreates(specsAndAttrs);
        for (MsSpecificationType msSpecificationType : msSpecificationTypes) {
            msSpecificationType.setSpecificationId(msSpecification.getSpecificationId());
            int insert1 = msSpecificationTypeMapper.insert(msSpecificationType);
            if (insert1 == 0) {
                return ResultSet.fail("添加规格失败");
            }
        }
        //添加规格值
        for(int i = 0; i < msSpecificationTypes.size(); i++){
            List<MsSpecificationValueCreate> valueCreates = specsAndAttrs.get(i).getSpecificationValues();
            List<MsSpecificationValue> values = MsSpecificationValueMapping.INSTANCE.fromCreates(valueCreates);
            for (MsSpecificationValue value : values) {
                value.setSpecificationTypeId(msSpecificationTypes.get(i).getSpecificationTypeId());
                int insert2 = msSpecificationValueMapper.insert(value);
                if (insert2 == 0) {
                    return ResultSet.fail("添加规格值失败");
                }
            }
        }
//        int insert = msSpecificationMapper.insert(msSpecification);
//        if(insert!=1){
//            return ResultSet.fail("添加规格失败");
//        }
        return ResultSet.success(null,"添加规格成功");
    }

    @Override
    public Page<MsSpecificationVo> getSpecificationList(String name, PageQuery pageQuery) {
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(name), MsSpecification::getSpecificationName, name);
        List<MsSpecificationVo> list = msSpecificationMapper.getAllSpecifications(lqw);
        Page<MsSpecificationVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), list.size());
        page.setRecords(list);
        return page;
    }

    @Override
    public ResultSet<MsSpecificationVo> getSpecificationById(Long id) {
        MsSpecificationVo msSpecificationVo = msSpecificationMapper.getSpecificationById(id);
        return ResultSet.success(msSpecificationVo);
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