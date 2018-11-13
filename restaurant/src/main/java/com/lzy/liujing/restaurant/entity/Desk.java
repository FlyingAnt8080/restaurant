package com.lzy.liujing.restaurant.entity;

import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:22:33
 */

public class Desk extends BaseBean{
    /**
     * 餐桌id
     */
    private Integer deskId;
    /**
     * 餐桌编号
     */
    @NotNull(message = "桌号必填")
    private String deskCode;
    /**
     * 座位人数
     */
    private Integer peopleCount;
    /**
     * 空闲状态(0空闲，1有人,2待清理)
     */
    private Integer idleStatus;

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public String getDeskCode() {
        return deskCode;
    }

    public void setDeskCode(String deskCode) {
        this.deskCode = deskCode;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(Integer idleStatus) {
        this.idleStatus = idleStatus;
    }

    @Override
    public String toString() {
        return "Desk{" +
                "deskId=" + deskId +
                ", deskCode='" + deskCode + '\'' +
                ", peopleCount=" + peopleCount +
                ", idleStatus=" + idleStatus +
                '}';
    }
}
