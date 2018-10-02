package com.lzy.liujing.restaurant.controller;

import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/index")
public class TestController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "index";
    }
    @RequestMapping("/user")
    @ResponseBody
    public List<SysUser> testUser(){
        return sysUserService.findAll();
    }
}
