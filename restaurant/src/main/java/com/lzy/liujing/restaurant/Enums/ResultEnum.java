package com.lzy.liujing.restaurant.Enums;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/2
 * Time:20:11
 */

public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误!"),
    SUCCESS(200,"成功!"),
    USER_NO_FOUND(101,"账号未找到!"),
    PWD_ERROR(101,"密码错误!"),
    FIELD_VALIDATION_FAILS(202,"验证失败");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
