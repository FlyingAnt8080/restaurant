package com.lzy.liujing.restaurant.entity;

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
    private Desk desk;
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

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", desk=" + desk +
                ", member=" + member +
                ", totalCost=" + totalCost +
                ", totalPrice=" + totalPrice +
                ", peopleNum=" + peopleNum +
                ", payStatus=" + payStatus +
                ", finishStatus=" + finishStatus +
                '}';
    }
}
