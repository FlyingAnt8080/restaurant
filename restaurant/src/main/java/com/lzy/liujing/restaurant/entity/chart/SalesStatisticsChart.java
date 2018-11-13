package com.lzy.liujing.restaurant.entity.chart;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 销售状况用到实体
 */
public class SalesStatisticsChart{
    @JsonIgnore
    private Integer day;
    private String date;
    private Double mustPay;
    private Double totalProfit;
    private Integer totalOrder;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Double getMustPay() {
        return mustPay;
    }

    public void setMustPay(Double mustPay) {
        this.mustPay = mustPay;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SalesStatisticsChart{" +
                "day=" + day +
                ", date='" + date + '\'' +
                ", mustPay=" + mustPay +
                ", totalProfit=" + totalProfit +
                ", totalOrder=" + totalOrder +
                '}';
    }
}
