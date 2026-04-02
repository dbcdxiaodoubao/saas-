package com.ruoyi.web.mapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.query.SysUserCreate;
import com.ruoyi.system.domain.vo.SysUserListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SysUserMapping {

    SysUserMapping INSTANCE = Mappers.getMapper(SysUserMapping.class);

    /*将租户添加转实体*/
    SysUser toCreate(SysUserCreate sysUserCreate);

    /*将租户实体转列表分页*/
    Page<SysUserListVo> toPage(Page<SysUser> sysUserPage);
}
