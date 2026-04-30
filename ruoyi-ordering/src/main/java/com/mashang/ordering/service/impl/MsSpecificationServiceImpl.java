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
import com.mashang.ordering.domain.param.update.MsSpecificationTypeUpdate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.param.update.MsSpecificationValueUpdate;
import com.mashang.ordering.domain.vo.MsSpecificationDto;
import com.mashang.ordering.domain.vo.MsSpecificationTypeVo;
import com.mashang.ordering.domain.vo.MsSpecificationValueVo;
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

    private boolean isSortReplace(Long sort,Long id){
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsSpecification::getSort, sort);
        lqw.ne(id!=null, MsSpecification::getSpecificationId, id);
        return msSpecificationMapper.selectCount(lqw) != 0;
    }

    private boolean isNameReplace(String name,Long id){
        LambdaQueryWrapper<MsSpecification> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsSpecification::getSpecificationName, name);
        lqw.ne(id!=null, MsSpecification::getSpecificationId, id);
        return msSpecificationMapper.selectCount(lqw) != 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate) throws Exception {
        //添加规格(组)
        MsSpecification msSpecification = MsSpecificationMapping.INSTANCE.fromCreate(msSpecificationCreate);
        if(isNameReplace(msSpecification.getSpecificationName(), null)){
            return ResultSet.fail("规格名重复");
        }
        if(isSortReplace(msSpecification.getSort(), null)){
            return ResultSet.fail("排序值重复");
        }
        int insert0 = msSpecificationMapper.insert(msSpecification);
        if (insert0 == 0) {
            throw new Exception("添加规格失败");
        }
        //添加规格类
        List<MsSpecificationTypeCreate> specsAndAttrs = msSpecificationCreate.getSpecsAndAttrs();
        List<MsSpecificationType> msSpecificationTypes = MsSpecificationTypeMapping.INSTANCE.fromCreates(specsAndAttrs);
        for (MsSpecificationType msSpecificationType : msSpecificationTypes) {
            msSpecificationType.setSpecificationId(msSpecification.getSpecificationId());
            int insert1 = msSpecificationTypeMapper.insert(msSpecificationType);
            if (insert1 == 0) {
                throw new Exception("添加规格失败");
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
                    throw new Exception("添加规格失败");
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultSet<Object> updateSpecification(MsSpecificationUpdate msSpecificationUpdate) throws Exception {
        int updateCount = 0;
        //更新规格组
        MsSpecification msSpecification = MsSpecificationMapping.INSTANCE.fromUpdate(msSpecificationUpdate);
        if(isNameReplace(msSpecification.getSpecificationName(), msSpecification.getSpecificationId())){
            return ResultSet.fail("规格名重复");
        }
        if(isSortReplace(msSpecification.getSort(), msSpecification.getSpecificationId())){
            return ResultSet.fail("排序值重复");
        }
        updateCount+=msSpecificationMapper.updateById(msSpecification);
        //更新规格类
        for(MsSpecificationTypeUpdate typeUpdate : msSpecificationUpdate.getSpecsAndAttrs()){
            MsSpecificationType msSpecificationType = MsSpecificationTypeMapping.INSTANCE.fromUpdate(typeUpdate);
            updateCount+=msSpecificationTypeMapper.updateById(msSpecificationType);
            //更新规格值
            for(MsSpecificationValueUpdate valueUpdate : typeUpdate.getSpecificationValues()){
                MsSpecificationValue msSpecificationValue = MsSpecificationValueMapping.INSTANCE.fromUpdate(valueUpdate);
                updateCount+=msSpecificationValueMapper.updateById(msSpecificationValue);
            }
        }
        if(updateCount == 0){
            return ResultSet.success(null,"更新规格成功");
        }else{
            return ResultSet.success(null,"规格未更新");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultSet<Object> deleteSpecification(Long id) throws Exception {
        LambdaQueryWrapper<MsProduct> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsProduct::getSpecificationId, id);
        if(msProductMapper.selectCount(lqw) != 0){
            throw new Exception("当前规格值正被商品所使用");
        }
        MsSpecificationVo msSpecificationVo = msSpecificationMapper.getSpecificationById(id);
        if(msSpecificationVo == null){
            throw new Exception("当前规格值不存在或已删除");
        }
        int delete = 0;
        for(MsSpecificationTypeVo typeVo : msSpecificationVo.getSpecsAndAttrs()){
            for(MsSpecificationValueVo valueVo : typeVo.getSpecificationValues()){
                delete = msSpecificationValueMapper.deleteById(valueVo.getSpecificationValueId());
                if(delete == 0){
                    throw new Exception("删除规格失败");
                }
            }
            delete = msSpecificationTypeMapper.deleteById(typeVo.getSpecificationTypeId());
            if(delete == 0){
                throw new Exception("删除规格失败");
            }
        }
        delete = msSpecificationMapper.deleteById(id);
        if(delete == 0){
            throw new Exception("删除规格失败");
        }
        return ResultSet.success(null,"删除规格成功");
    }
}