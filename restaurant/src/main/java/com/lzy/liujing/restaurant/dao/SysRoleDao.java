package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.SysRole;
import com.lzy.liujing.restaurant.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:23:32
 */
@Mapper
public interface SysRoleDao extends tk.mybatis.mapper.common.Mapper<SysRole>{
    Set<String> findRoleNamesByUser(SysUser sysUser);
    List<SysRole> findAll();
}

