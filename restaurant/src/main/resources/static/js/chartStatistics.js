layui.config({
    base: '/restaurant/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
}).use(['index']);

require.config({
    paths: {
        echarts: '/restaurant/js/dist'
    }
});
layui.use(['index', 'console'], function () {
    var $ = layui.$
    //查询每天(8-24)点中每小时的顾客网络请求
    todayCustomerFlowChartNetworkRequest();
    //默认初始化近七天的菜系销量统计
    chart2NetworkRequest(6);
    //菜名销量排行词云
    goodsWordCloudNetworkRequest(6);
    //默认初始化近七天的会员增加数量统计
    memberAddChartNetworkRequest(6);
    //各类型会员比率网络请求
    memberCategoryRateNetworkRequest();
    /**
     * 菜系销售分析网络请求数据
     */
    function todayCustomerFlowChartNetworkRequest(){
        $.ajax({
            url: '/restaurant/analysis/peopleByHour.do',
            type: "GET",
            dataType: "JSON",
            success: function (res) {
                if (res.code == 200) {
                    var data = res.data;
                    var timeArray = [];
                    var countArray = [];
                    $.each(data, function (index, item) {
                        timeArray.push(item.dayHour);
                        countArray.push(item.count);
                    });
                    //今日客流量面积图
                    loadTodayCustomerFlowChart(timeArray,countArray);
                } else {
                    layer.msg(res.msg, {icon: 5});
                }
            }
        });
    }
    /**
     * 菜系销售分析网络请求数据
     */
    function chart2NetworkRequest(day) {
        $.ajax({
            url: '/restaurant/analysis/gcsByDays.do',
            type: "GET",
            dataType: "JSON",
            data: {
                day: day
            },
            success: function (res) {
                if (res.code == 200) {
                    var data = res.data;
                    var nameArray = [];
                    var countArray = [];
                    for (var i = 0; i < data.length; i++) {
                        nameArray.push(data[i].categoryName);
                        var valueAndName = {}
                        valueAndName.name = data[i].categoryName;
                        valueAndName.value = data[i].count;
                        countArray.push(valueAndName);
                    }
                    loadChart2(nameArray, countArray);
                } else {
                    layer.msg(res.msg, {icon: 5});
                }
            }
        });
    }

    /**
     * 各类型会员比率网络请求
     */
    function memberCategoryRateNetworkRequest() {
        $.ajax({
            url: '/restaurant/analysis/mcRate.do',
            type: "GET",
            dataType: "JSON",
            success: function (res) {
                if (res.code == 200) {
                    var data = res.data;
                    var nameArray = [];
                    var countArray = [];
                    $.each(data, function (index, item) {
                        nameArray.push(item.categoryName);
                        var valueAndName = {}
                        valueAndName.name = item.categoryName;
                        valueAndName.value = item.count;
                        countArray.push(valueAndName);
                    });
                    loadMemberCategoryRateChart(nameArray, countArray);
                } else {
                    layer.msg(res.msg, {icon: 5});
                }
            }
        });
    }

    //随机生成颜色
    function createRandomItemStyle() {
        return {
            normal: {
                color: 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')'
            }
        }
    }

    /**
     * 菜品销售词云网络请求数据
     */
    function goodsWordCloudNetworkRequest(day) {
        $.ajax({
            url: '/restaurant/analysis/gwcByDays.do',
            type: "GET",
            dataType: "JSON",
            data: {
                day: day
            },
            success: function (res) {
                if (res.code == 200) {
                    var data = res.data;
                    var chartData = [];
                    $.each(data, function (ind, item) {
                        chartData.push({
                            name: item.goodsName,
                            value: Math.pow(item.count, 3),
                            itemStyle: createRandomItemStyle()
                        });
                    })
                    //加载词云
                    loadGoodsWordCloudChart(chartData);
                } else {
                    layer.msg(res.msg, {icon: 5});
                }
            }
        });
    }

    /**
     * 近几天增长会员数量网络请求
     */
    function memberAddChartNetworkRequest(day) {
        $.ajax({
            url: '/restaurant/analysis/mAddByDays.do',
            type: "GET",
            dataType: "JSON",
            data: {
                day: day
            },
            success: function (res) {
                if (res.code == 200) {
                    var data = res.data;
                    var dateArray = [];
                    var countArray = [];
                    $.each(data, function (ind, item) {
                        dateArray.push(item.date);
                        countArray.push(item.count);
                    })
                    if(data.length!=0){
                        //加载近几天会员增加数量折线图
                        loadMemberAddChart(dateArray, countArray);
                    }
                } else {
                    layer.msg(res.msg, {icon: 5});
                }
            }
        });
    }
});

/**
 * 当天顾客流量面积图
 */
function loadTodayCustomerFlowChart(timeArray,countArray){
    //引入图表文件
    require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar',
        ],
        function(echarts) {
            var customerFlowChart = echarts.init(document.getElementById('customerFlowChart'), 'macarons');
            var customerFlowChartOption = {
                title : {
                    text: '今日客流量',
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['人数']
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : timeArray
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'人数',
                        type:'line',
                        smooth:true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data:countArray
                    }
                ]
            };
            customerFlowChart.setOption(customerFlowChartOption);
        });

}
/**
 * 加载近几天会员增加数量折线图
 */
function loadMemberAddChart(dateArray, countArray) {
    require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar'
        ],
        function (echarts) {
            var memberAddChart = echarts.init(document.getElementById('memberAddChart'), 'macarons');
            var memberAddChartOption = {
                title: {
                    text: '近七天新增会员量',
                    //subtext: '最近七天新增会员量'
                },
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: dateArray
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}人'
                        }
                    }
                ],
                series: [
                    {
                        name:'人数',
                        type: 'line',
                        data: countArray,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            memberAddChart.setOption(memberAddChartOption);
            window.onresize = function () {
                memberAddChart.resize();
            }
        });
}

/**
 * 加载各会员比率 饼状图表
 */
function loadMemberCategoryRateChart(nameArray, countArray) {
    require(
        [
            'echarts',
            'echarts/chart/pie',
            'echarts/chart/funnel'
        ],
        function (echarts) {
            var MemberCategoryRateChart = echarts.init(document.getElementById('memberCategoryRate'), 'macarons');
            var MemberCategoryRateChartOption = {
                title: {
                    text: '各等级会员统计',
                    //subtext:'各等级会员统计',
                    x: 'left'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'right',
                    data: nameArray
                },
                calculable: false,
                series: [
                    {
                        name: '类型名称',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: countArray
                    }
                ]
            };
            MemberCategoryRateChart.setOption(MemberCategoryRateChartOption);
        });
}

/**
 * 加载菜系销量统计 饼状图表
 */
function loadChart2(nameArray, countArray) {
    require(
        [
            'echarts',
            'echarts/chart/pie',
            'echarts/chart/funnel'
        ],
        function (echarts) {
            var myChart2 = echarts.init(document.getElementById('chart2'), 'macarons');
            var option2 = {
                title: {
                    text: '近七天菜系销量分析',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: nameArray
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {
                            show: true,
                            type: ['funnel','pie'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1548
                                }
                            }
                        },
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: false,
                series: [
                    {
                        name: '菜系',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: countArray
                    }
                ]
            };
            myChart2.setOption(option2);
        });
}

/**
 * 加载菜品热销词云
 */
function loadGoodsWordCloudChart(chartData) {
    var maskImage = new Image();
    maskImage.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAFj0lEQVR4Xu2bd6gkRRCHvzPnjBgwK/6hJ4oRzAHMiIoZRVExK+YsYkCMGFExoKJgQFBQTJgPA4gJFTFnVMwBc+A7emBunNnpmZ2e2z1fwbLv7XZXV/+6pqq6qnYSo0+LAzsD6wKrAysB3wJfAG8C94fXN222MqnNpB7mzAbsBRwCrB2x3p/ANcCZwNc145cGTgZOc2wZAKsCr0UsmmrIBsBdwGItFvge2Al4rGTuksCpwP7AfUGrKAKwIPABcBhwawsBhp2yO3ALMOsQjH4DtgkgrAZsHf5fH5g58F0PeN6/iwAcDlwBqFLbAw8OIUjTqbsAdzadVDH+12AntB9FuhY4OPuwCMCjwGbhy58Dek93JNQgNhq354DZE6/1ErAp4KMylYoA/ATMnRPil6AJApOKVMu3geVSLRD4Ph724sG6b23N8nkAFga+KhHiD0D1vDeRgBql6xPxztheDKj6awDagl0BPc3kPAArAO9UCPI3cDpwHvBPx8K+DyzbMc88O7X6L2D+3Ie/A5sDU/IALAV8VCOIarRHCEK6kFkr/UoXjBrw8DB3CK5wGhsgGCJVFxz5mBwJ3N6BNpwUtKqB/EMP3Q+4qcoIfgosEbmEJ2dE9UDk+LJhV+dd0hB8YqeeAFyYH1w8bTezVSy3MO4Z4BLgnqBBTabfHSK3JnPajNXtafgeLk4uArB3iMTaLPJZQNcIssyblPE00NqyzWIN51wJHFE2pwjAHCGC8r0taUeeBO4IMb03tyq6Ddiz7UIN5hndarf+Q2UGT3U+ugHzQUO1uG8BL4bXy8B7wdsI1LnAKR2tNYjNBcCJsQAYILwKrJxQMIHR4ApCyhgg28IZwNmxADhucojN50oIQp+s1ehL6wBYB1gx+HdPaC3gIWChPiVNtNY0vj+/Rt4GbAIY6X0ZQDAp8TnwBGAyYZxpY+CpOg1YZTpnglIC7AHqpgd6Ae/iBgyp7+QpN1rG2yt9pS0rukEzrKaTZiRS9X0ESqkIwG7h+Z+RAND96QajAJgJeLcn39wXyFsAlRmtskhw2+yu3JeECdcxGaIbN6sVpQHZIC80FibGnW4G9h20iarkh4lRr8YbjjkCXu0N5ippUPZHd3hjT7e1FDgb0On/rXG0AiCbZA7wcmCRFFIm5Gntz9vmQKrL/2WT5wGsGvk8pbwl1skb+71ZX2uLg3IRU3nVAaBHUIWst0lGVNYI9gF0maNK5hoPjRGuDoADgOtiGI3QmB+t+MSm5eoAUPU/BhYYoQ3WiVJ59y+bWAeAcw4KzQd1C4/C928AFlvMNEVRDACOMSboI3sbJXTFIG3VmiGdF80nBgCZGRhZJre4OKpk7fKcpsLFAiDfOUPXiC0oo0bPhnK3qbxG1ASAjLEdW6aZtbSjQEZ8JnF9b0xtAHARe3issOomzSVOryySt7yNQga78ead0BaA/GJu3gyyEeJZPSdQPYAbWu08TOoCAFm5ectcWuG+6HjgomEXGxYA+3uODVUXK0p9UWWlp6kAbQGYF7DYcFTPxlArfwxwWdONVo1vCoBdpG78QEAQ+iR7gXXBVp47ozoAtPZa2e2AHYFlOlu5GaMpgF2kFlQ7JQGwbcwGAq25KM8XOqqy9zqQOhWowMykpm04V3XQj1QqZ7Y5jZn9wYaSfat2FYA2WBwHfJIS4eLpmvY6P+QBh+kSGUZm1d1uDttak1OVeqsFhrymxu0dTp39+QGwYcpOcavRvVHM871o0AgrLBrELh4RQ9jXgReAR0IbbpZ2623zLhQDQF4gNcGEg0BYcDTt7FXZzFH2miVUmb8L7/6C40PAllh/i2BjtD2GJi6nOzUFYLoL3LUAEwB0jei48ZvQgHE7sa7lndCArhEdN34TGjBuJ9a1vBMa0DWi48bvf68B/wLfzty6qBF5fAAAAABJRU5ErkJggg==";
    require(
        [
            'echarts',
            'echarts/chart/wordCloud'
        ],
        function (ec) {
            var goodsWordCloudChart = ec.init(document.getElementById('goodsWordCloudChart'), 'macarons');
            var goodsWordCloudChartOption = {
                title: {
                    text: '近七天热销菜',
                },
                tooltip: {
                    show: true
                },
                series: [{
                    name: '菜名',
                    type: 'wordCloud',
                    center: ['50%', '50%'],
                    size: ['80%', '80%'],
                    textRotation: [0, 45, 90, -45],
                    maskImage: maskImage,
                    textPadding: 0,
                    autoSize: {
                        enable: true,
                        minSize: 16
                    },
                    data: chartData
                }]
            };
            goodsWordCloudChart.setOption(goodsWordCloudChartOption)
        })
}


