package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.mapper.MsSpecificationMapper;
import com.mashang.ordering.service.IMsSpecificationService;
import org.springframework.stereotype.Service;

@Service
public class MsSpecificationServiceImpl extends ServiceImpl<MsSpecificationMapper, MsSpecification>
        implements IMsSpecificationService {


}