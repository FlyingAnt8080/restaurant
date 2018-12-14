package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.entity.SysRole;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.entity.request.UserRequest;
import com.lzy.liujing.restaurant.service.SysUserService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.apache.catalina.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户列表数据接口
     * @param userRequest
     * @return
     */

    @PostMapping("/userlist.do")
    @RequiresPermissions("userManage:view")
    @ResponseBody
    public Result<List<SysUser>> userList(UserRequest userRequest){
        SysRole role = new SysRole();
        role.setRoleId(userRequest.getRoleId());
        userRequest.setRole(role);
        CustomPageInfo pageInfo = sysUserService.findPage(userRequest);
        Result<List<SysUser>> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 用户列表html页面
     * @param model
     * @return
     */
    @GetMapping("/userlist.html")
    @RequiresPermissions("userManage:view")
    public String userList(Model model){
        model.addAttribute("roleList",sysUserService.findRoleList());
        return "user/userList";
    }

    /**
     * 添加用户页面
     * @return
     */
    @GetMapping("/adduser.html")
    @RequiresPermissions("userManage:edit")
    public String addUser(Model model){
        model.addAttribute("roleList",sysUserService.findRoleList());
        return "user/addUser";
    }

    @PostMapping("/adduser.do")
    @RequiresPermissions("userManage:edit")
    @ResponseBody
    public Result<SysUser> addUser(SysUser user,SysRole role){
        user.setRole(role);
        sysUserService.addUser(user);
        return ResultUtil.success();
    }

    /**
     * 编辑用户页面
     * @return
     */

    @GetMapping("/edituser.html/{userId}")
    @RequiresPermissions("userManage:edit")
    public String editUser(@PathVariable("userId") Long userId,Model model){
        SysUser user = sysUserService.findById(userId);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = sf.format(user.getBirthday());
        model.addAttribute("user",user);
        model.addAttribute("birthday",birthday);
        model.addAttribute("roleList",sysUserService.findRoleList());
        return "user/editUser";
    }

    @PostMapping("/saveEdit.do")
    @ResponseBody
    public Result<SysUser> saveEdit(SysUser user,SysRole role,HttpSession session){
        user.setRole(role);
        sysUserService.update(user);
        SysUser curUser = (SysUser) session.getAttribute("user");
        //如果果是当前修改当前用户信息，则要将session中的保存的当前用户重新设置
        if(curUser.getUserId()==user.getUserId()){
            SysUser sysUser = sysUserService.findById(user.getUserId());
            session.setAttribute("user",sysUser);
        }
        return ResultUtil.success();
    }

    @PostMapping("/del.do")
    @RequiresPermissions("userManage:edit")
    @ResponseBody
    public Result<SysUser> delete(@RequestParam String ids){
        sysUserService.deleteByIds(ids);
        return ResultUtil.success("删除成功！");
    }


    @GetMapping("/resetpwd/{userId}")
    @RequiresPermissions("userManage:edit")
    @ResponseBody
    public Result<SysUser> resetPwd(@PathVariable("userId") Long userId){
        SysUser user = new SysUser();
        user.setPassword("123456");
        user.setUserId(userId);
        sysUserService.resetPwd(user);
        return ResultUtil.success();
    }

    /**
     * 个人信息页面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/curuserinfo.html")
    public String currentUserInfo(HttpSession session,Model model){
        SysUser currentUser = (SysUser) session.getAttribute("user");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("currentUser", currentUser);
        String birthday = sf.format(currentUser.getBirthday());
        model.addAttribute("birthday",birthday);
        model.addAttribute("roleList",sysUserService.findRoleList());
        return "user/userInfo";
    }

    /**
     * 修改密码页面
     * @return
     */
    @GetMapping("/updatepwd.html")
    public String updatePwd(){
        return "user/updatePwd";
    }

    @PostMapping("/updatepwd.do")
    @ResponseBody
    public Result<SysUser> updatePwd(SysUser user){
        sysUserService.updatePwd(user);
        return ResultUtil.success();
    }
}
