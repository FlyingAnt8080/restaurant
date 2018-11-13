package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.dao.DataAnalysisDao;
import com.lzy.liujing.restaurant.entity.chart.*;
import com.lzy.liujing.restaurant.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图表数据分析统计业务逻辑
 */
@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {
    @Autowired
    private DataAnalysisDao dataAnalysisDao;

    /**
     * 查询近几天天的商品销量排行top
     * day 天数
     * number top数
     * @return
     */
    @Override
    public List<GoodsSalesChart> findSalesSortByDays(GoodsSalesChart goodsSalesChart) {
        return dataAnalysisDao.findSalesSortByDays(goodsSalesChart);
    }
    /**
     * 查询近几天的菜系销量数据
     * @param gcsChart
     * @return
     */
    @Override
    public List<GoodsCategorySalesChart> findGoodsCategorySalesByDays(GoodsCategorySalesChart gcsChart) {
        return dataAnalysisDao.findGoodsCategorySalesByDays(gcsChart);
    }

    /**
     * 查询近几天的营业情况数据
     * @param salesStatisticsChart
     * @return
     */
    @Override
    public List<SalesStatisticsChart> findSalesStatisticsByDays(SalesStatisticsChart salesStatisticsChart){
        return dataAnalysisDao.findSalesStatisticsByDays(salesStatisticsChart);
    }
    /**
     * 查询近几天会员增长情况
     * @param memberChart
     * @return
     */
    @Override
    public List<MemberChart> findMemberAddByDays(MemberChart memberChart) {
        return dataAnalysisDao.findMemberAddByDays(memberChart);
    }
    /**
     * 查询各类型会员的数量
     * @return
     */
    @Override
    public List<MemberChart> findMemberCategoryRate() {
        return dataAnalysisDao.findMemberCategoryRate();
    }
    /**
     * 查询每天各个小时的顾客量
     * @return
     */
    @Override
    public List<PeopleChart> findPeopleByHours() {
        return dataAnalysisDao.findPeopleByHours();
    }
}
