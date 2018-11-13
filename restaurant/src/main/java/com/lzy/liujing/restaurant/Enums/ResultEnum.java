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
    USER_IS_EXIST(101,"账号已注册！"),
    PHONE_IS_EXIST(101,"电话号已存在!"),
    ID_NUMBER_IS_EXIST(101,"身份证号已存在！"),
    MC_NAME_IS_EXIST(101,"会员类型已存在！"),
    DESK_CODE_IS_EXIST(101,"桌位编号已存在！"),
    DESK_CODE_NO_EXIST(101,"桌位编号未找到！"),
    DESK_CODE_NO_IDLE(101,"桌位不空闲！"),
    CATEGORY_NAME_IS_EXIST(101,"菜品类型已存在!"),
    GOODS_NAME_IS_EXIST(101,"菜品名称已存在！"),
    FIELD_VALIDATION_FAILS(202,"验证失败！"),
    INSERT_DB_FAIL(101,"数据添加失败！"),
    DEL_DB_FAIL(101,"删除失败！"),
    UPDATE_DB_FAIL(101,"修改失败！"),
    IMAGE_UPLOAD_FAIL(101,"图片上传失败！"),
    UNAUTHENTICATED_ERROR(100,"没有权限"),
    ADD_ORDER_FAIL(101,"添加订单失败");
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
