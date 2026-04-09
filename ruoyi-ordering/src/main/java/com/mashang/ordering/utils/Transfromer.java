package com.mashang.ordering.utils;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashang.ordering.domain.entity.MsSpecificationType;

import java.util.List;

public class Transfromer {

    public static String toJsonString(List<MsSpecificationType> msSpecifications) {
        return JSON.toJSONString(msSpecifications);
    }
}
