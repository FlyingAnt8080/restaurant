package com.lzy.liujing.restaurant.entity.request;

import com.lzy.liujing.restaurant.entity.SysUser;

public class UserRequest extends SysUser {
    private int pageNum;
    private int pageSize;
    private Integer roleId;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
