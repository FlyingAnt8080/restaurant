package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.SysUser;

import java.util.List;

/**
 * 员工管理业务逻辑
 */
public interface SysUserService {
    List<SysUser> findAll();
    SysUser findByLoginCode(SysUser sysUser);
}
