package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.service.SysUserService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/2
 * Time:15:20
 *
 * 登录模块接口
 */
@Controller
@RequestMapping("/sysuser")
public class LoginController {
   @Autowired
   private SysUserService sysUserService;
    /**
     *  @Valid 表示验证该对象   验证结果保存到BindingResult对象中
     */
    @PostMapping(value = "/login.do")
    @ResponseBody
    public Result<SysUser> login(@Valid SysUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //202 验证失败
            return ResultUtil.error(202,bindingResult.getFieldError().getDefaultMessage());
        }
        System.out.println("成功");
        return ResultUtil.success(sysUserService.findByLoginCode(user));
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login.html")
    public String login(){
        return "/login/login";
    }

    /**
     * 主页面
     * @return
     */
    @GetMapping("/admin.html")
    public String index(){
        return "/admin";
    }
}
