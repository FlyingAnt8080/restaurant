package com.lzy.liujing.restaurant.utils;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.entity.Result;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/2
 * Time:17:53
 * 返回值工具类
 */

public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    public static Result success(Object object,Long count){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        result.setCount(count);
        return result;
    }
    public static Result success(){
        return  success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return  result;
    }
}
