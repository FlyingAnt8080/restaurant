package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao extends tk.mybatis.mapper.common.Mapper<SysUser>{
    /**
     * 查询所有用户
     */
    List<SysUser> findAll();

    /**
     * 根据账号查询
     * @return
     */
    SysUser findByLoginCode(String loginCode);

    /**
     * 修改删除
     * @param sysUser
     * @return
     */
    int update(SysUser sysUser);

    /**
     * 插入用
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);
}
