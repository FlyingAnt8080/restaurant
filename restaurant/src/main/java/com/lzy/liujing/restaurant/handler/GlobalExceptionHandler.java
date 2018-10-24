package com.lzy.liujing.restaurant.handler;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.exception.CustomAuthenticationException;
import com.lzy.liujing.restaurant.exception.CustomException;
import com.lzy.liujing.restaurant.exception.SysUserImplException;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/2
 * Time:19:56
 */
@ControllerAdvice//注解定义全局异常处理类
public class GlobalExceptionHandler {
    //Spring自带的日志框架Logger
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //声明要捕获的异常类
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(Exception e){
        if(e instanceof UnauthenticatedException){
            UnauthenticatedException exception = new UnauthenticatedException();
            return ResultUtil.error(ResultEnum.UNAUTHENTICATED_ERROR.getCode(),ResultEnum.UNAUTHENTICATED_ERROR.getMsg());
        }
        if(e instanceof UnauthorizedException){
            return ResultUtil.error(ResultEnum.UNAUTHENTICATED_ERROR.getCode(),ResultEnum.UNAUTHENTICATED_ERROR.getMsg());
        }
        if(e instanceof SysUserImplException){
            SysUserImplException sysUserImplException = (SysUserImplException) e;
            return ResultUtil.error(sysUserImplException.getCode(),sysUserImplException.getMessage());
        }else if(e instanceof CustomAuthenticationException){
            CustomAuthenticationException customAuthenticationException = (CustomAuthenticationException) e;
            return ResultUtil.error(customAuthenticationException.getCode(),customAuthenticationException.getMessage());
        }else if(e instanceof CustomException){
            CustomException exception = (CustomException) e;
            return ResultUtil.error(exception.getCode(),exception.getMessage());
        }else{
            logger.info("[系统错误]={}",e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }
}
