package com.lzy.liujing.restaurant.entity;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:22:55
 * 会员类型类
 */

public class MemberCategory extends BaseBean{
    /**
     * 会员类型id
     */
    private  Integer mcId;
    /**
     * 会员类型名称
     */
    private  String mcName;
    /**
     * 会员享有的折扣
     */
    private String discount;
    /**
     * 需要达到的消费金额
     */
    private Double amount;

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public String getMcName() {
        return mcName;
    }

    public void setMcName(String mcName) {
        this.mcName = mcName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
