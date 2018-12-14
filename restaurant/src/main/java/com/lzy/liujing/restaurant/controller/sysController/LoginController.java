package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.service.SysUserService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
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
public class LoginController{
   @Autowired
   private SysUserService sysUserService;
    /**登录功能
     *  @Valid 表示验证该对象   验证结果保存到BindingResult对象中
     */
    @PostMapping(value = "/login.do")
    @ResponseBody
    public Result<SysUser> login(@Valid SysUser user, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            //202 验证失败
            return ResultUtil.error(202,bindingResult.getFieldError().getDefaultMessage());
        }

        //获取主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginCode(),user.getPassword());
        System.out.println(session.getMaxInactiveInterval());
        //将登录交个shiro
        subject.login(token);
        //登录成功后
        return ResultUtil.success();
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout.do")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //清除session
        subject.getSession().removeAttribute("user");
        return "redirect:login.html";
    }
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login.html")
    public String login(HttpSession session){
        return "login/login";
    }

    /**
     * 主页面
     * @return
     */
    @GetMapping("/admin.html")
    public String admin(HttpSession session, Model model){
        SysUser sysUser = (SysUser) session.getAttribute("user");
        //查询菜单格式后的结构树并返回视图
       model.addAttribute("menuList",sysUserService.findMenuTreeByUser(sysUser));
        return "admin";
    }

    /**
     * 控制台页面
     * @return
     */
   @GetMapping("/console.html")
    public String console(){
        return "home/console";
    }
    @GetMapping("/403.html")
    public String eorror(){
       return "eorror/403";
    }
}
