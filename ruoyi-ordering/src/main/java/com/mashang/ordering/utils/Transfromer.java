package com.mashang.ordering.utils;

import com.alibaba.fastjson2.JSON;
import com.mashang.ordering.domain.entity.MsSpecificationType;

import java.util.List;

public class Transfromer {

    public static String toJsonString(List<MsSpecificationType> msSpecifications) {
        return JSON.toJSONString(msSpecifications);
    }

    //我们项目怎么莲pageQuery都不统一下……
    public static com.mashang.ordering.domain.common.PageQuery rPage2oPage(
            com.ruoyi.common.core.page.PageQuery rPage
    ) {
        com.mashang.ordering.domain.common.PageQuery oPage = new com.mashang.ordering.domain.common.PageQuery();
        oPage.setPageNum(rPage.getPageNum());
        oPage.setPageSize(rPage.getPageSize());
        return oPage;
    }

    public static com.ruoyi.common.core.page.PageQuery oPage2rPage(
            com.mashang.ordering.domain.common.PageQuery oPage
    ) {
        com.ruoyi.common.core.page.PageQuery rPage = new com.ruoyi.common.core.page.PageQuery();
        rPage.setPageNum(oPage.getPageNum());
        rPage.setPageSize(oPage.getPageSize());
        return rPage;
    }
}
