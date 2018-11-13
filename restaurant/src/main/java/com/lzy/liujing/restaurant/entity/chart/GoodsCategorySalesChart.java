package com.lzy.liujing.restaurant.entity.chart;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GoodsCategorySalesChart{
    private String categoryName;
    private Integer count;
    @JsonIgnore
    private Integer day;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public String toString() {
        return "GoodsCategorySalesChart{" +
                "categoryName='" + categoryName + '\'' +
                ", count=" + count +
                ", day=" + day +
                '}';
    }
}
