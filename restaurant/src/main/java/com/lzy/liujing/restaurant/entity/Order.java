package com.lzy.liujing.restaurant.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:23:12
 *
 * 订单表
 */

public class Order extends BaseBean{
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 餐桌
     */
    private String deskCode;
    /**
     * 收银员
     */
    private SysUser cashier;
    /**
     * 会员
     */
    private Member member;
    /**
     * 总成本
     */
    private Double totalCost;
    /**
     * 总价格
     */
    private Double totalPrice;
    /**
     * 总利润
     */
    private Double totalProfit;
    /**
     * 实际付款
     */
    private Double actualPay;
    /**
     * 应付金额
     */
    private Double mustPay;
    /**
     * 找零
     */
    private Double changeMoney;
    /**
     * 折扣金额
     */
    private  Double discountMoney;
    /**
     * 就餐人数
     */
    private Integer peopleNum;
    /**
     * 支付状态(0未支付，1已支付)
     */
    private Integer payStatus;
    /**
     *上菜是否完成（0未完成，1完成）
     */
    private Integer finishStatus;
    /**
     * 订单结束状态(0未结束，1已结束)
     */
    private Integer overStatus;
    /**
     * 用于根据时间段查询，开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//用接口传入String类型时间转换成date类型
    private Date startTime;
    /**
     * 用于根据时间段查询，结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//用接口传入String类型时间转换成date类型
    private Date endTime;

    private List<OrderDetail> orderDetails;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost){
        this.totalCost = totalCost;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    public String getDeskCode() {
        return deskCode;
    }

    public void setDeskCode(String deskCode) {
        this.deskCode = deskCode;
    }

    public Double getActualPay() {
        return actualPay;
    }

    public void setActualPay(Double actualPay) {
        this.actualPay = actualPay;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getMustPay() {
        return mustPay;
    }

    public void setMustPay(Double mustPay) {
        this.mustPay = mustPay;
    }

    public Double getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Double discountMoney){
        this.discountMoney = discountMoney;
    }

    public SysUser getCashier() {
        return cashier;
    }

    public void setCashier(SysUser cashier) {
        this.cashier = cashier;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getOverStatus() {
        return overStatus;
    }

    public void setOverStatus(Integer overStatus) {
        this.overStatus = overStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", deskCode='" + deskCode + '\'' +
                ", cashier=" + cashier +
                ", member=" + member +
                ", totalCost=" + totalCost +
                ", totalPrice=" + totalPrice +
                ", totalProfit=" + totalProfit +
                ", actualPay=" + actualPay +
                ", mustPay=" + mustPay +
                ", changeMoney=" + changeMoney +
                ", discountMoney=" + discountMoney +
                ", peopleNum=" + peopleNum +
                ", payStatus=" + payStatus +
                ", finishStatus=" + finishStatus +
                ", overStatus=" + overStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
