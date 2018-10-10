package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.dao.SysMenuDao;
import com.lzy.liujing.restaurant.dao.SysRoleDao;
import com.lzy.liujing.restaurant.dao.SysUserDao;
import com.lzy.liujing.restaurant.entity.SysMenu;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.exception.SysUserImplException;
import com.lzy.liujing.restaurant.service.SysUserService;
import com.lzy.liujing.restaurant.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 员工管理业务逻辑实现类
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.selectAll();
    }

    @Override
    public SysUser findByLoginCode(String loginCode) {
        return sysUserDao.findByLoginCode(loginCode);
    }

    /**
     * 根据角色查询所拥有的权限
     * @param sysMenu
     * @return
     */
    @Override
    public List<SysMenu> findMenuList(SysMenu sysMenu){
        return sysMenuDao.findList(sysMenu);
    }

    /**
     * 根据用户查找角色
     * @param sysUser
     * @return
     */
    public Set<String> findRoleNameByUser(SysUser sysUser){
        return sysRoleDao.findRoleNamesByUser(sysUser);
    }
    /**
     * 根据用户查询所拥有的功能菜单
     * @param sysUser
     * @return
     */
    @Override
    public List<SysMenu> findMenuTreeByUser(SysUser sysUser){
        Set<String> roles = sysRoleDao.findRoleNamesByUser(sysUser);
        SysMenu sysMenu = new SysMenu();
        sysMenu.getCondition().put("list",roles);
        //根据角色信息，查询所拥有的菜单
        //格式化菜单
        List<SysMenu> formatMenuList = MenuUtil.formatMenuList(sysMenuDao.findList(sysMenu),false);
        return formatMenuList;
    }
}
