package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.entity.request.UserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao extends tk.mybatis.mapper.common.Mapper<SysUser>{
    /**
     * 分页查询
     * @return
     */
    List<SysUser> findPage(UserRequest userRequest);

    /**
     * 根据账号查询
     * @return
     */
    SysUser findByLoginCode(String loginCode);
    /**
     * 根据id查用户
     * @return
     */
    SysUser findById(Long id);

    /**
     * 查找账号是否存在
     * @param loginCode
     * @return
     */
    SysUser searchLoginCode(String loginCode);

    SysUser searchPhone(SysUser sysUser);

    SysUser findByIdNumber(SysUser idNumber);

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

    /**
     * 根据id删除用户
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
