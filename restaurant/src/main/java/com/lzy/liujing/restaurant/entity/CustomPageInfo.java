package com.lzy.liujing.restaurant.entity;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/3
 * Time:15:41
 *
 * 分页查询类
 */

public class CustomPageInfo<T> extends PageInfo<T> {
    /**
     * 用于储存实体
     */
    private T t;
    /**
     * 排序方式
     */
    private String orderBy;
    /**
     * 条件
     */
    private Map<String,Object> condition;
    public CustomPageInfo(){

    }
    public T getT() {
        return t;
    }

    public CustomPageInfo(T t){
        this.t = t;
    }

    public CustomPageInfo(List<T> list){
        super(list);
    }

    @Override
    public void setPageSize(int pageSize) {
        if(pageSize>50)pageSize = 50;
        super.setPageSize(pageSize);
    }

    public void setT(T t){
        this.t = t;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
}
