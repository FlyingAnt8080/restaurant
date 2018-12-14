package com.lzy.liujing.restaurant.auth;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.entity.SysMenu;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.exception.CustomAuthenticationException;
import com.lzy.liujing.restaurant.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 认证
     * 获取身份效验信息
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String loginCode = (String) token.getPrincipal();
       //此处username 即loginCode登录账号
        SysUser user = sysUserService.findByLoginCode(loginCode);
        if(user==null){
            //此处自定义的AuthenticationException
            throw new CustomAuthenticationException(ResultEnum.USER_NO_FOUND);
        }else if(!user.getPassword().equals(new String((char[]) token.getCredentials()))){
            throw new CustomAuthenticationException(ResultEnum.PWD_ERROR);
        }
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user",user);
        //身份信息
        return new SimpleAuthenticationInfo(
                user,//用户
                user.getPassword(),//密码
                ByteSource.Util.bytes(loginCode),
                getName()//realm name
        );
    }

    /**
     * 授权
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户的输入的账号.
        String loginCode = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getLoginCode();
        //根据账号查询用户
        SysUser param = sysUserService.findByLoginCode(loginCode);
        //授权信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = sysUserService.findRoleNameByUser(param);
        info.setRoles(roles);
        SysMenu sysMenu = new SysMenu();
        sysMenu.getCondition().put("list",roles);
        //根据角色信息，查询出用户具备的权限字符串
        List<SysMenu> menuList = sysUserService.findMenuList(sysMenu);
        Set<String> permissions = new HashSet<>();
        for(SysMenu menu:menuList){
            if(!StringUtils.isEmpty(menu.getPermission())){
                permissions.add(menu.getPermission());
                System.out.println(menu.getPermission());
            }
        }
        // 将权限名称提供给info
        //set集合不会出现重复数据
        info.setStringPermissions(permissions);
        return info;
    }
}
