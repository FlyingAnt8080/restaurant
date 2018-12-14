package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.entity.chart.*;
import com.lzy.liujing.restaurant.service.DataAnalysisService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 图表分析接口
 */
@Controller
@RequestMapping("/analysis")
public class DataAnalysisController{
    @Autowired
    private DataAnalysisService dataAnalysisService;
    /**
     * 销量统计HTML页面
     * @return
     */
    @GetMapping("/salesVolume.html")
    public String salesVolume(){
        return "chart/salesVolume";
    }

    /**
     * 销售统计HTML页面
     * @return
     */
    @GetMapping("/salesStatistics.html")
    public String salesStatistics(){
        return "/chart/salesStatistics";
    }

    /**
     * 查询近几天的菜品销量排行top10数据接口
     * @return
     */
    @GetMapping("/gssByDays.do")
    @ResponseBody
    public Result<GoodsSalesChart> goodsSalesSortByDays(GoodsSalesChart goodsSalesChart){
        //默认查询top12
        goodsSalesChart.setNumber(12);
        List<GoodsSalesChart> result = dataAnalysisService.findSalesSortByDays(goodsSalesChart);
        return ResultUtil.success(result,new Long(result.size()));
    }
    /**
     * 查询近几天的菜品销量排行词云数据接口
     * @return
     */
    @GetMapping("/gwcByDays.do")
    @ResponseBody
    public Result<GoodsSalesChart> goodsWordCloudBynDays(GoodsSalesChart goodsSalesChart){
        //默认查询top12
        goodsSalesChart.setNumber(20);
        List<GoodsSalesChart> result = dataAnalysisService.findSalesSortByDays(goodsSalesChart);
        return ResultUtil.success(result,new Long(result.size()));
    }
    /**
     * 查询近几天的菜系销量数据
     * @return
     */
    @GetMapping("/gcsByDays.do")
    @ResponseBody
    public Result<GoodsCategorySalesChart> goodsCategorySalesByDays(GoodsCategorySalesChart gcsChart){
        List<GoodsCategorySalesChart> result = dataAnalysisService.findGoodsCategorySalesByDays(gcsChart);
        return ResultUtil.success(result,new Long(result.size()));
    }

    /**
     * 查询近几天的营业情况数据
     * @return
     */
    @GetMapping("/ssByDays.do")
    @ResponseBody
    public Result<SalesStatisticsChart> salesStatisticsByDay(SalesStatisticsChart ssChart){
        List<SalesStatisticsChart> result = dataAnalysisService.findSalesStatisticsByDays(ssChart);
        return ResultUtil.success(result,new Long(result.size()));
    }

    /**
     * 查询近几天会员增长数接口
     * @param memberChart
     * @return
     */
    @GetMapping("/mAddByDays.do")
    @ResponseBody
    public Result<MemberChart> memberAddByDays(MemberChart memberChart){
        List<MemberChart> result  = dataAnalysisService.findMemberAddByDays(memberChart);
        return ResultUtil.success(result,new Long(result.size()));
    }

    /**
     * 查询各类型会员数量
     * @return
     */
    @GetMapping("/mcRate.do")
    @ResponseBody
    public Result<MemberChart> memberCategoryRate(){
        List<MemberChart> result  = dataAnalysisService.findMemberCategoryRate();
        return ResultUtil.success(result,new Long(result.size()));
    }
    /**
     * 查询每天(8-24)点中每小时的顾客数量接口
     * @return
     */
    @GetMapping("/peopleByHour.do")
    @ResponseBody
    public Result<PeopleChart> peopleByHours(){
        List<PeopleChart> result  = dataAnalysisService.findPeopleByHours();
        return ResultUtil.success(result,new Long(result.size()));
    }
}
