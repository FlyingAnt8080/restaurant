package com.lzy.liujing.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.dao.SysMenuDao;
import com.lzy.liujing.restaurant.dao.SysRoleDao;
import com.lzy.liujing.restaurant.dao.SysUserDao;
import com.lzy.liujing.restaurant.dao.UserRoleDao;
import com.lzy.liujing.restaurant.entity.*;
import com.lzy.liujing.restaurant.entity.request.UserRequest;
import com.lzy.liujing.restaurant.exception.CustomException;
import com.lzy.liujing.restaurant.service.SysUserService;
import com.lzy.liujing.restaurant.utils.MenuUtil;
import com.lzy.liujing.restaurant.utils.SplitIdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 员工管理业务逻辑实现类
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 根据账号查询
     *
     * @param loginCode
     * @return
     */
    @Override
    public SysUser findByLoginCode(String loginCode) {
        return sysUserDao.findByLoginCode(loginCode);
    }

    /**
     * 根据角色查询所拥有的权限
     *
     * @param sysMenu
     * @return
     */
    @Override
    public List<SysMenu> findMenuList(SysMenu sysMenu) {
        return sysMenuDao.findList(sysMenu);
    }

    /**
     * 根据用户查找角色
     *
     * @param sysUser
     * @return
     */
    public Set<String> findRoleNameByUser(SysUser sysUser) {
        return sysRoleDao.findRoleNamesByUser(sysUser);
    }

    /**
     * 根据用户查询所拥有的功能菜单
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<SysMenu> findMenuTreeByUser(SysUser sysUser) {
        Set<String> roles = sysRoleDao.findRoleNamesByUser(sysUser);
        SysMenu sysMenu = new SysMenu();
        sysMenu.getCondition().put("list", roles);
        //根据角色信息，查询所拥有的菜单
        //格式化菜单
        List<SysMenu> formatMenuList = MenuUtil.formatMenuList(sysMenuDao.findList(sysMenu), false);
        return formatMenuList;
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.selectAll();
    }

    /**
     * 修改用户信息
     * @param sysUser
     */
    @Override
    public void update(SysUser sysUser) throws CustomException {
        SysUser findUser = sysUserDao.searchPhone(sysUser);
        if(findUser!=null){
            throw new CustomException(ResultEnum.PHONE_IS_EXIST);
        }
        findUser = sysUserDao.findByIdNumber(sysUser);
        if(findUser!=null){
            throw new CustomException(ResultEnum.ID_NUMBER_IS_EXIST);
        }
        sysUser.setModifyTime(new Date());
        sysUserDao.update(sysUser);
        UserRole userRole = new UserRole();
        userRole.setUserId(sysUser.getUserId());
        userRole.setRoleId(sysUser.getRole().getRoleId());
        if(sysUser.getRole().getRoleId()!=null){
            userRoleDao.update(userRole);
        }
    }

    /**
     * 添加用户
     * @param sysUser
     */
    @Override
    public void addUser(SysUser sysUser) throws CustomException{
        SysUser findUser = sysUserDao.searchLoginCode(sysUser.getLoginCode());
        if (findUser != null) {
            //code 101 msg 账号已注册
            throw new CustomException(ResultEnum.USER_IS_EXIST);
        }
        //电话不能重复
        findUser = sysUserDao.searchPhone(sysUser);
        if(findUser!=null){
            throw new CustomException(ResultEnum.PHONE_IS_EXIST);
        }
        findUser = sysUserDao.findByIdNumber(sysUser);
        if (findUser != null) {
            //code 101 msg 身份证号不能重复
            throw new CustomException(ResultEnum.ID_NUMBER_IS_EXIST);
        }
        sysUser.setCreateTime(new Date());
        //初始密码
        sysUser.setPassword("123456");
        try {
            sysUser.setModifyTime(new Date());
            sysUserDao.insert(sysUser);
            UserRole userRole = new UserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(sysUser.getRole().getRoleId());
            userRoleDao.insert(userRole);
        } catch (Exception e) {
            throw new CustomException(ResultEnum.INSERT_DB_FAIL);
        }
    }

    /**
     * 分页查询
     * @param userRequest
     * @return
     */
    @Override
    public CustomPageInfo<SysUser> findPage(UserRequest userRequest) {
        //PageHelper的分页参数
        Page page = PageHelper.startPage(userRequest.getPageNum(), userRequest.getPageSize());
        /**
         * 此处掉查询方法后,会将值返回到保存到page中，这是pagehelper的机制(原理有待研究)
         */
        sysUserDao.findPage(userRequest);
        //采用构造方法创建对象,
        CustomPageInfo<SysUser> resultPageInfo = new CustomPageInfo<>(page);
        return resultPageInfo;
    }

    /**
     * 查询角色表所有的角色
     *
     * @return
     */
    @Override
    public List<SysRole> findRoleList() {
        return sysRoleDao.findAll();
    }

    /**
     * 根据id删除用户
     */
    @Override
    public void deleteByIds(String strIds) throws CustomException{
        List<Long> idList = SplitIdsUtil.splitStrIds(strIds);
        int effectNum = sysUserDao.deleteByIds(idList);
        if(effectNum<0){
            //删除失败 code 101 msg "删除失败"
            throw new CustomException(ResultEnum.DEL_DB_FAIL);
        }
    }
    /**
     * 根据id查用户
     * @return
     */
    @Override
    public SysUser findById(Long id) {
        return sysUserDao.findById(id);
    }

    /**
     * 重置密码
     * @param sysUser
     */
    public void resetPwd(SysUser sysUser){
        sysUserDao.update(sysUser);
    }

    /**
     * 修改密码
     * @param sysUser
     */
    @Override
    public void updatePwd(SysUser sysUser) {
       SysUser findUser = sysUserDao.findById(sysUser.getUserId());
       if(!findUser.getPassword().equals(sysUser.getOldPwd())){
           throw  new CustomException(ResultEnum.PWD_ERROR);
       }
       SysUser user = new SysUser();
       user.setUserId(sysUser.getUserId());
       user.setPassword(sysUser.getNewPwd());
       sysUserDao.update(user);
    }
}
