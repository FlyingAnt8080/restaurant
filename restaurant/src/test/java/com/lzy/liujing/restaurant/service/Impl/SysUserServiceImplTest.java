package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {
    @Autowired
    private SysUserService sysUserService;
    @Test
    public void findAll() {
        System.out.println(sysUserService.findAll());
    }
}