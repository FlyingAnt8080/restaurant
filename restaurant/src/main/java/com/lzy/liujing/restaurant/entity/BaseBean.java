package com.lzy.liujing.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BaseBean {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date modifyTime;
    /**
     * 删除标志(0未删除，1删除)
     */
    protected Integer del;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", del=" + del +
                '}';
    }
}
