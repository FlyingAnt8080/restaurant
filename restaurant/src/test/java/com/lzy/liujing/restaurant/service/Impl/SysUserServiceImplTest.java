package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.SysRole;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.entity.request.UserRequest;
import com.lzy.liujing.restaurant.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {
    @Autowired
    private SysUserService sysUserService;
    @Test
    public void findAll() {
        System.out.println(sysUserService.findAll());
    }
    @Test
    public void findPage(){
        SysUser sysUser = new SysUser();
        SysRole sysRole = new SysRole();
        /*sysRole.setRoleId(1L);
        sysUser.setRole(sysRole);
        sysUser.setGender("男");*/
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        sysUser.setBirthday(date);
        UserRequest userRequest = new UserRequest();
        userRequest.setPageNum(1);
        userRequest.setPageSize(2);
        CustomPageInfo<SysUser> users = sysUserService.findPage(userRequest);
    }
}