package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.chart.*;

import java.util.List;

public interface DataAnalysisService {
    /**
     * 查询近几天天的商品销量排行top
     * day 天数
     * number top数
     * @return
     */
     List<GoodsSalesChart> findSalesSortByDays(GoodsSalesChart goodsSalesChart);
    /**
     * 查询近几天的菜系销量数据
     * @param goodsCategorySalesChart
     * @return
     */
    List<GoodsCategorySalesChart> findGoodsCategorySalesByDays(GoodsCategorySalesChart goodsCategorySalesChart);

    /**
     * 查询近几天的营业情况数据
     * @param salesStatisticsChart
     * @return
     */
    List<SalesStatisticsChart> findSalesStatisticsByDays(SalesStatisticsChart salesStatisticsChart);

    /**
     * 查询近几天会员增长情况
     * @param memberChart
     * @return
     */
    List<MemberChart> findMemberAddByDays(MemberChart memberChart);

    /**
     * 查询各类型会员的数量
     * @return
     */
    List<MemberChart> findMemberCategoryRate();
    /**
     * 查询每天各个小时的顾客量
     * @return
     */
    List<PeopleChart> findPeopleByHours();
}
