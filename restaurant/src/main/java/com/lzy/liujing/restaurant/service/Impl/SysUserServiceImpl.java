package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.dao.SysUserDao;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.exception.SysUserImplException;
import com.lzy.liujing.restaurant.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工管理业务逻辑实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public List<SysUser> findAll() {
        return sysUserDao.selectAll();
    }

    @Override
    public SysUser findByLoginCode(SysUser sysUser) {
        SysUser user = sysUserDao.findByLoginCode(sysUser);
        if(user!=null){
            if(user.getPassword().equals(sysUser.getPassword())){
                return user;
            }else{
                throw new SysUserImplException(ResultEnum.PWD_ERROR);
            }
        }else{
            //返回 用户未找到 code=101
            throw new SysUserImplException(ResultEnum.USER_NO_FOUND);
        }
    }
}
