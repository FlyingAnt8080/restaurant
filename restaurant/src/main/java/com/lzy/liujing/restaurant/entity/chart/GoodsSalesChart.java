package com.lzy.liujing.restaurant.entity.chart;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GoodsSalesChart {
    private String goodsName;
    private Integer count;
    @JsonIgnore
    private Integer day;
    @JsonIgnore
    private Integer number;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GoodsSalesChart{" +
                "goodsName='" + goodsName + '\'' +
                ", count=" + count +
                ", day=" + day +
                ", number=" + number +
                '}';
    }
}
