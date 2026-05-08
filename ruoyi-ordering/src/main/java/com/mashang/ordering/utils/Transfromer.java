package com.mashang.ordering.utils;

import com.alibaba.fastjson2.JSON;
import com.mashang.ordering.domain.entity.MsSpecificationType;

import java.util.List;

public class Transfromer {

    public static com.ruoyi.common.core.page.PageQuery oPage2rPage(
            com.mashang.ordering.domain.common.PageQuery oPage
    ) {
        com.ruoyi.common.core.page.PageQuery rPage = new com.ruoyi.common.core.page.PageQuery();
        rPage.setPageNum(oPage.getPageNum());
        rPage.setPageSize(oPage.getPageSize());
        return rPage;
    }
}
