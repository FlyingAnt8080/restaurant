package com.lzy.liujing.restaurant.entity;

import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:23:19
 *
 * 订单明细表
 */

public class OrderDetail extends BaseBean{
    /**
     * 订单明细id
     */
    private Long odId;
    /**
     * 订单
     */
    private Order order;
    /**
     * 商品
     */
    private Goods goods;
    /**
     * 菜的数量
     */
    private Integer count;
    /**
     * 菜的状态(0未制作，1正在制作，2制作完成，3上菜完成)
     */
    private Integer status;
    /**
     * 制菜说明
     */
    private String description;
    /**
     * 订单id集
     */
    private List<Long> odIdList;

    public Long getOdId() {
        return odId;
    }

    public void setOdId(Long odId) {
        this.odId = odId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getOdIdList() {
        return odIdList;
    }

    public void setOdIdList(List<Long> odIdList) {
        this.odIdList = odIdList;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "odId=" + odId +
                ", order=" + order +
                ", goods=" + goods +
                ", count=" + count +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", odIdList=" + odIdList +
                '}';
    }
}
