package com.lzy.liujing.restaurant.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建订单号工具类
 */
public class OrderCodeUtil {
    public static String createOrderCode(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MMdd-HHmm-ss-SSS");
        Date date = new Date();
        return sf.format(date);
    }
}
