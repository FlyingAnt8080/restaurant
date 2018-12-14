package com.lzy.liujing.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.dao.DeskDao;
import com.lzy.liujing.restaurant.dao.GoodsDao;
import com.lzy.liujing.restaurant.dao.OrderDao;
import com.lzy.liujing.restaurant.dao.OrderDetailDao;
import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Desk;
import com.lzy.liujing.restaurant.entity.Order;
import com.lzy.liujing.restaurant.entity.OrderDetail;
import com.lzy.liujing.restaurant.exception.CustomException;
import com.lzy.liujing.restaurant.service.CookAndServingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 做菜上菜管理业务逻辑
 */
@Service
@Transactional
public class CookAndServingServiceImpl implements CookAndServingService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DeskDao deskDao;

    /**
     * 制菜任务列表查询
     * @param pageInfo
     * @return
     */
    @Override
    public List<OrderDetail> cookTaskFindPage(CustomPageInfo<OrderDetail> pageInfo) {
        List<Integer> statusList = new ArrayList<>();
        //查询正在制作的菜
        statusList.add(1);
        List<OrderDetail> resultCookTaskList = orderDetailDao.findPageByStatus(statusList);
        //根据菜品id合并汇总
        cookTaskManage(resultCookTaskList);
        //查询所有等待制作的
        statusList.clear();
        statusList.add(0);
        List<OrderDetail> cookingTaskList = orderDetailDao.findPageByStatus(statusList);
        //根据菜品id合并汇总
        cookTaskManage(cookingTaskList);
        //等待烹饪的菜品列表放在正在烹饪的菜品列表后面
        resultCookTaskList.addAll(cookingTaskList);
        return resultCookTaskList;
    }

    /**
     * 将查询出的数据根据，菜品id合并汇总
     * @param cookList
     */
    @Override
    public void cookTaskManage(List<OrderDetail> cookList) {
        //有序
        Map<Integer, OrderDetail> map = new LinkedHashMap<>();
        for (OrderDetail detail : cookList){
            Integer key = detail.getGoods().getGoodsId();
            //找到相同的菜时
            if (map.containsKey(key)) {
                OrderDetail od = map.get(key);
                //将明细id添加的到明细id集
                od.getOdIdList().add(detail.getOdId());
                //将菜的数量相加
                od.setCount(od.getCount()+detail.getCount());
                map.put(key, od);
            } else {
                //第一次找到该菜，初始化明细id集
                detail.setOdIdList(new ArrayList<>());
                //将明细id添加到明细id集
                detail.getOdIdList().add(detail.getOdId());
                map.put(key, detail);
            }
        }
        //清空列表
        cookList.clear();
        //重新赋值
        cookList.addAll(map.values());
    }

    /**
     * 根据odId,明细id集合修改明细完成状态
     * @param orderDetail
     */
    @Override
    public void updateStatusByOdIds(OrderDetail orderDetail){
       int effect = orderDetailDao.updateStatusByOdIds(orderDetail);
       if(effect<=0){
           throw new CustomException(ResultEnum.UPDATE_DB_FAIL);
       }
    }

    /**
     * 上菜任务列表分页查询
     *
     * @param pageInfo
     * @return
     */
    @Override
    public CustomPageInfo<OrderDetail> servingTaskFindPage(CustomPageInfo<OrderDetail> pageInfo) {
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Integer> statusList = new ArrayList<>();
        statusList.add(2);
        orderDetailDao.findPageByStatus(statusList);
        CustomPageInfo<OrderDetail> resultInfo = new CustomPageInfo<>(page);
        return resultInfo;
    }

    /**
     * 完成上菜业务
     *
     * @param orderDetail
     */
    public void finishServing(OrderDetail orderDetail) {
        //状态3为完成上菜
        orderDetail.setStatus(3);
        orderDetailDao.update(orderDetail);
        //查看订单菜所有明细
        OrderDetail query = new OrderDetail();
        query.setOrder(orderDetail.getOrder());
        List<OrderDetail> queryOrderDetailList = orderDetailDao.findPage(query);
        int count = 0;
        for (OrderDetail queryOrderDetail : queryOrderDetailList) {
            if (queryOrderDetail.getStatus() == 3) {
                count++;
            }
        }
        /**
         * 已完成上菜数量和订单中菜的总数量相同
         * 将订单修改成完成状态
         */
        if (count == queryOrderDetailList.size()){
            Order order = orderDetail.getOrder();
            order.setFinishStatus(1);
            /**
             * 该订单如果已付款，且已完成全部上菜
             */
            if(queryOrderDetailList.get(0).getOrder().getPayStatus()==1){
                order.setOverStatus(1);
                //将对应餐桌状态设置成'待清理'
                String currDeskCode = queryOrderDetailList.get(0).getOrder().getDeskCode();
                Desk desk = new Desk();
                desk.setDeskCode(currDeskCode);
                desk.setIdleStatus(2);
                deskDao.update(desk);
            }
            orderDao.update(order);
        }
    }
}
