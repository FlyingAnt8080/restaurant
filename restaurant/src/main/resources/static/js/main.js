/*定义几个全局变量*/
var Jquery;
var orderCode='';
var popHtml = '<div class="container layui-card" style="width: 98%;height: 98%;margin: 1%;background:#F2F2F2">\n' +
    '                <div class="layui-card top-item"\n' +
    '                     style="top:0px;height:100px;margin:0px;color: #0C0C0C;overflow:hidden;text-align: center">\n' +
    '                    <ul style="overflow: hidden;margin: 36px 5px 35px 10px">\n' +
    '                        <li style="float: left">\n' +
    '                            <div style="font-size: 20px;color: red">\n' +
    '                                <span style="height:30px;text-align: center;line-height: 30px">合计:￥</span>\n' +
    '                                <input type="text" id="totalMoney"\n' +
    '                                       style="width: 60px;height: 30px;text-align: center;line-height: 30px;border: none;background:none;color: red" value="0.00" disabled></li>\n' +
    '                        <li style="float: right">\n' +
    '                            <button class="layui-btn" style="background:#AF2825;width:64px;height: 30px;padding:2px 4px;line-height: 30px;text-align: center;font-size: 14px;border-radius: 6px"; id="commit">提交</button>\n' +
    '                        </li>\n' +
    '                        <li style="float: left;margin-left: 26px">\n' +
    '                           <div style="overflow: hidden;display: inline-block">\n' +
    '                                <span class="input-float" style="height: 30px;text-align: center;line-height: 30px">就餐人数</span>' +
    '                                <input class="input-float people_minus" type="button"\n' +
    '                                       style="width: 25px;font-size:20px;height: 30px;background: none;border: 0px"\n' +
    '                                       value="-" name="minus">\n' +
    '                                <input class="input-float people_number" type="text" name="people_number"\n' +
    '                                       style="width: 50px;height: 30px;border: 0px;text-align: center" disabled\n' +
    '                                       value="1">\n' +
    '                                <input class="input-float people_plus" type="button"\n' +
    '                                       style="width: 25px;font-size:20px;height: 30px;background: none;border: 0px"\n' +
    '                                       value="+">\n' +
    '                           </div>\n' +
    '                        </li>\n' +
    '                    </ul>\n' +
    '                </div>\n' +
    '                <div class="goods-items" id="goodsItems"></div>'
var popDom;

/**
 * 初始化总金额
 * @param goodsItemsNode
 */
function countTotalMoney(goodsItemsNode) {
    var topItemNode = popDom.find(".top-item");
    var itemArray = goodsItemsNode.children();
    var totalMoneyNode = topItemNode.find("#totalMoney");
    var totalMoney = 0.00;
    itemArray.each(function (index, item) {
        var price = parseFloat(Jquery(item).find("input[name='price']").val());
        var number = parseInt(Jquery(item).find("input[name='number']").val());
        totalMoney += price * number;
    })
    totalMoneyNode.val(parseFloat(totalMoney).toFixed(2));
}

//添加菜到菜单
function addGoodsByOrder(goods) {
    var goodsItemHtml = parseGoodSView(goods);
    var goodsItemsNode = popDom.find(".goods-items");
    goodsItemsNode.append(Jquery(goodsItemHtml));
    countTotalMoney(goodsItemsNode);
}

//将数据填充到HTML模板中
function parseGoodSView(goods) {
    var goodsItemHtml =
        '<ul class="layui-card goods-item">\n' +
        '<input type="hidden" name="goodsId" value="' + goods.goodsId + '">\n' +
        '<input type="hidden" name="storeCount" value="' + goods.storeCount + '">\n' +
        '<li class="goods-item-li-other" style=" width: 20%;">\n' +
        '                            <img src="' + goods.imgSrc + '" class="goods-item-image">\n' +
        '                        </li>\n' +
        '                        <li class="goods-item-li-other" style="padding:20px 0px 20px 3%; width: 22%;">\n' +
        '                            <span class="name"\n' +
        '                                  style="display:inline-block;line-height: 30px;height:30px;font-size:14px;color: #0C0C0C">' + goods.goodsName + '</span><br/>\n' +
        '                            <span style="display:inline-block;line-height: 30px;height:30px;color: red"><input\n' +
        '                                    type="text" style="width: 30px;border: none;background:none;color: red" name="price"\n' +
        '                                    value="' + goods.price + '" disabled>元/份</span>\n' +
        '                        </li>\n' +
        '                        <li class="goods-item-li-other" style="text-align: center;padding:35px 0px;width: 25%;"\n' +
        '                            disabled="">\n' +
        '                            <div style="overflow: hidden;display: inline-block">\n' +
        '                                <input class="input-float minus" type="button"\n' +
        '                                       style="width: 25px;font-size:20px;height: 30px;background: none;border: 0px"\n' +
        '                                       value="-" name="minus">\n' +
        '                                <input class="input-float number" type="text" name="number"\n' +
        '                                       style="width: 50px;height: 30px;border: 0px;text-align: center" disabled\n' +
        '                                       value="1">\n' +
        '                                <input class="input-float plus" type="button"\n' +
        '                                       style="width: 25px;font-size:20px;height: 30px;background: none;border: 0px"\n' +
        '                                       value="+">\n' +
        '                            </div>\n' +
        '                        </li>\n' +
        '                        <li class="goods-item-li-other" style="text-align: center;width: 15%">\n' +
        '                            <input type="text"\n' +
        '                                   style="width: 90%;height: 30px;margin:35px auto;background:none;border: 0px;text-align: center;color: red"\n' +
        '                                   disabled name="money" value="' + goods.price + '"/>\n' +
        '                        </li>\n' +
        '                        <li class="goods-item-li-other" style="text-align: center;width: 15%">\n' +
        '                            <button class="layui-btn layui-btn-primary delete"\n' +
        '                                    style="margin:35px auto;color: #FFFFFF;background: #AF2825;height:30px;line-height:30px;text-align:center;border-radius: 6px;border: 0px;">\n' +
        '                                删除\n' +
        '                            </button>\n' +
        '                        </li>\n' +
        '                    </ul>'
    return goodsItemHtml;
}

layui.config({
    base: '../layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use('index');
layui.use(['index', "jquery"], function () {
    var $ = layui.$
        , admin = layui.admin
        , element = layui.element
        , router = layui.router();
    Jquery = $;
    //将html字符串转化成dom对象
    popDom = Jquery(popHtml);
    var topItemNode = popDom.find(".top-item");
    var goodsItemsNode = popDom.find(".goods-items");
    var active = {
        test2: function () {
            top.layui.admin.popupRight({
                id: 'LAY_adminPopupLayerTest'
                , area: '500px'
                , success: function (){
                    $('#' + this.id).css({"width": "100%", "height": "100%", "background": "#f2f2f2"});
                    $('#' + this.id).html(popDom);

                    //先清除一次事件,防止重复绑定事件
                    $('#commit').off('click');
                    //给提交按钮添加点击事件
                    $("#commit").on("click", function (e) {
                        layer.confirm('确定提交?提交后将不能修改！', function (index) {
                            confirm();
                            layer.close(index);
                        });

                        function confirm() {
                            var goodsItemArray = (goodsItemsNode).children(".goods-item");
                            var order = {}
                            var orderDetails = []

                            //定义一个订单明细对象
                            function orderDetail(count, goods) {
                                this.count = count;
                                this.goods = goods;
                            }

                            for (var i = 0; i < goodsItemArray.size(); i++) {
                                var goods = {};
                                goods.goodsId = parseInt($(goodsItemArray[i]).find("input[name='goodsId']").val());
                                var count = parseInt($(goodsItemArray[i]).find("input[name='number']").val());
                                orderDetails.push(new orderDetail(count, goods));
                            }
                            order.orderDetails = orderDetails;
                            order.deskCode = $("#deskCode").text();
                            var peopleNode = popDom.find("input[name='people_number']");
                            console.log("peopleNum", $(peopleNode).val());
                            order.peopleNum = parseInt($(peopleNode).val());
                            order.totalPrice = parseFloat($("#totalMoney").val());
                            var data = JSON.stringify(order);
                            $.ajax({
                                url: '/restaurant/guest/client/addorder.do',
                                type: 'post',
                                data: data,
                                contentType: "application/json; charset=utf-8",
                                dataType: 'JSON',
                                success: function (res) {
                                    if (res.code == 200) {
                                        orderCode = res.data;
                                        popDom.empty();
                                        //注意通过a标签跳转Iframe页面，有几个页面会生成几个iframe
                                        //清除所有iframe页面中的已选菜品样式
                                        clearAllIframeStyle();
                                        layer.msg("提交成功！", {icon: 1})
                                    } else {
                                        layer.msg("提交失败！", {icon: 2})
                                    }
                                }
                            });
                        }
                    })
                    showOrHideCommit();
                    //先清除一次事件,防止重复绑定事件
                    $('.delete').off('click');
                    $(".delete").on("click", function (e) {
                        //.parents获取出来是一个数组
                        //注意 goodsItem 等是dom对象,要查找子节点要加$()变成jquery对象，才能用jquery的方法
                        var goodsItem = $(this).parents(".goods-item")[0];
                        var goodsItems = $(this).parents(".goods-items")[0];
                        goodsItems.removeChild(goodsItem);
                        //注意通过a标签跳转Iframe页面，有几个页面会生成几个iframe
                        for (var i = 0; i < $(".layadmin-iframe").size(); i++) {
                            var childWindow = $(".layadmin-iframe")[i].contentWindow;
                            childWindow.clearChoice($(goodsItem).find("input[name='goodsId']").val());
                        }
                        //该菜的总金额
                        var itemMoney = parseFloat($(goodsItem).find("input[name='money']").val());
                        var curtotalMoney = parseFloat($("#totalMoney").val());
                        $("#totalMoney").val((curtotalMoney - itemMoney).toFixed(2));
                        showOrHideCommit();
                    });
                    //先清除一次事件,防止重复绑定事件
                    $('.plus').off('click');
                    //添加数量
                    $(".plus").on("click", function (e) {
                        var goodsItem = $(this).parents(".goods-item");
                        //prev() 上一个兄弟节点
                        var currentCount = parseInt($(this).prev().val());
                        var storeCount = $(goodsItem).find("input[name='storeCount']").val();
                        if (currentCount >= 1) {
                            layer.msg("不要点重了哦!", {icon: 6});
                        }
                        if (currentCount >= storeCount) {
                            layer.msg("没有那么多份亲！", {icon: 5});
                        } else {
                            $(this).prev().attr("value", currentCount + 1);
                            var price = parseFloat($(goodsItem).find("input[name='price']").val());
                            $(goodsItem).find("input[name='money']").attr("value", price * (currentCount + 1));
                            var currentMoney = parseFloat($("#totalMoney").val());
                            $("#totalMoney").val((currentMoney + price).toFixed(2));
                        }
                    });
                    //先清除一次事件,防止重复绑定事件
                    $('.minus').off('click');
                    $(".minus").on("click", function (e) {
                        var goodsItem = $(this).parents(".goods-item");
                        //next()下一个兄弟节点
                        var currentCount = parseInt($(this).next().val());
                        if (currentCount > 1) {
                            $(this).next().attr("value", currentCount - 1);
                            var price = parseFloat($(goodsItem).find("input[name='price']").val());
                            $(goodsItem).find("input[name='money']").attr("value", (price * (currentCount - 1)).toFixed(2));
                            var curTotalMoney = parseFloat($("#totalMoney").val());
                            $("#totalMoney").val((curTotalMoney - price).toFixed(2));
                        }
                    });
                    //先清除一次事件,防止重复绑定事件
                    $('.people_plus').off('click');
                    //添加数量
                    $(".people_plus").on("click", function () {
                        //prev() 上一个兄弟节点
                        var currentCount = parseInt($(this).prev().val());
                        $(this).prev().attr("value", currentCount + 1);
                    });
                    //先清除一次事件,防止重复绑定事件
                    $('.people_minus').off('click');
                    $(".people_minus").on("click", function (e) {
                        //next()下一个兄弟节点
                        var currentCount = parseInt($(this).next().val());
                        if (currentCount > 1) {
                            $(this).next().attr("value", currentCount - 1);
                        }
                    });
                }
            });
        }
    };

    /**
     * 弹窗
     */
    $('#goods').on('click', function () {
        var type = $(this).data('type');
        active[type] && active[type].call(this);
    });

    $("#commit_goods").on('click',function (){
        if(orderCode==''){
            layer.msg("还没有点菜哦！",{icon:6});
            return;
        }
        var url = "/restaurant/guest/client/myOrder.html?orderCode="+orderCode;
        layer.open({
            type: 2
            , title: '已提交菜单'
            , content: url
            , maxmin: true
            , anim: 1
            , area: ['900px', '500px']
        });
    });
    /**
     * 隐藏或显示提交按钮
     */
    function showOrHideCommit(){
        var totalMoney = parseFloat($("#totalMoney").val());
        if (totalMoney == 0) {
            $("#commit").hide();
        }else {
            $("#commit").show();
        }
    }

    /**
     * 清除所有iframe页面已选择菜的样式
     */
    function clearAllIframeStyle() {
        console.log("iframeSize", $(".layadmin-iframe").size());
        for (var i = 0; i < $(".layadmin-iframe").size(); i++) {
            var childWindow = $(".layadmin-iframe")[i].contentWindow;
            childWindow.clearAllChoice();
        }
    }
});