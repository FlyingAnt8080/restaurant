package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.SysMenu;
import com.lzy.liujing.restaurant.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * 员工管理业务逻辑
 */
public interface SysUserService {
    List<SysUser> findAll();

    /**
     * 登录
     * @param loginCode
     * @return
     */
    SysUser findByLoginCode(String loginCode);

    /**
     * 根据角色查询菜单
     * @param sysMenu
     * @return
     */
    List<SysMenu> findMenuList(SysMenu sysMenu);

    /**
     * 根据用户查找角色
     * @param sysUser
     * @return
     */
    Set<String> findRoleNameByUser(SysUser sysUser);

    /**
     * 根据用户查找格式后的菜单树
     * @param sysUser
     * @return
     */
    List<SysMenu> findMenuTreeByUser(SysUser sysUser);
}
