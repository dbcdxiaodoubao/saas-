package com.mashang.ordering.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class FieldHandler implements MetaObjectHandler {
    /**
     * 插入时自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 严格对应你的 BaseModel 字段
        this.setFieldValByName("createBy", SecurityUtils.getUsername(), metaObject);
        this.setFieldValByName("createTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);

    }

    /**
     * 更新时自动填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 这里不能有空格！！！
        this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);
    }

}