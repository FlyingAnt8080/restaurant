package com.lzy.liujing.restaurant.entity.chart;

public class PeopleChart {
    private String dayHour;
    private Integer count;

    public String getDayHour() {
        return dayHour;
    }

    public void setDayHour(String dayHour) {
        this.dayHour = dayHour+":00";
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PeopleChart{" +
                "dayHour='" + dayHour + '\'' +
                ", count=" + count +
                '}';
    }
}
