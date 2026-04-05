package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsMeal;
import com.mashang.ordering.domain.entity.MsMealMenu;
import com.mashang.ordering.mapper.MsMealMapper;
import com.mashang.ordering.mapper.MsMealMenuMapper;
import com.mashang.ordering.service.IMsMealMenuService;
import com.mashang.ordering.service.IMsMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsMealMenuServiceImpl extends ServiceImpl<MsMealMenuMapper, MsMealMenu> implements IMsMealMenuService {

}
