package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.mapper.MsProductMapper;
import com.mashang.ordering.service.IMsProductService;
import org.springframework.stereotype.Service;

@Service
public class MsProductServiceImpl extends ServiceImpl<MsProductMapper, MsProduct> implements IMsProductService {
}
