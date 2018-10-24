package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.SysMenu;
import com.lzy.liujing.restaurant.entity.SysRole;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.entity.request.UserRequest;

import java.util.List;
import java.util.Set;

/**
 * 员工管理业务逻辑
 */
public interface SysUserService {

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
    /**
     * 查询所有用户
     */
    List<SysUser> findAll();
    /**
     * 修改删除
     * @param sysUser
     * @return
     */
    void update(SysUser sysUser);

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    void addUser(SysUser sysUser);

    /**
     * 分页查询，已经多条件分页查询
     * @param userRequest
     * @return
     */
    CustomPageInfo<SysUser> findPage(UserRequest userRequest);

    /**
     * 查找角色列表
     * @return
     */
    List<SysRole> findRoleList();

    /**
     *根据id删除用户
     */
    void deleteByIds(String ids);

    /**
     * 根据id查用户
     * @return
     */
    SysUser findById(Long id);
    void resetPwd(SysUser sysUser);
    void updatePwd(SysUser sysUser);
}
