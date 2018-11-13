package com.lzy.liujing.restaurant.entity.request;

public class OrderRequest {
    private Long goodsId;
    private Integer count;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "goodsId=" + goodsId +
                ", count=" + count +
                '}';
    }
}
