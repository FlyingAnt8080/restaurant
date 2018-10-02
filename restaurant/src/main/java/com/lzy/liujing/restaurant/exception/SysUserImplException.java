package com.lzy.liujing.restaurant.exception;

import com.lzy.liujing.restaurant.Enums.ResultEnum;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/2
 * Time:17:13
 */
/**
 * 异常异常类
 * 注意：此处为什么继承RuntimeException而不是Exception与？
 * 原因：Spring这个框架只对抛出的异常是RuntimeException才做事务回滚，如果抛出Exception是不会进行事务回滚的
 */
public class SysUserImplException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SysUserImplException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
