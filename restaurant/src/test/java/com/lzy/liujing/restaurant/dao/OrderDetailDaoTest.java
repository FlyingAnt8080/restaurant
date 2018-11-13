package com.lzy.liujing.restaurant.dao;

import com.github.pagehelper.PageHelper;
import com.lzy.liujing.restaurant.entity.Goods;
import com.lzy.liujing.restaurant.entity.Member;
import com.lzy.liujing.restaurant.entity.Order;
import com.lzy.liujing.restaurant.entity.OrderDetail;
import com.lzy.liujing.restaurant.utils.SplitIdsUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao detailDao;
    @Test
    public void findPage(){
        /*PageHelper.startPage(1,5);
        OrderDetail orderDetail = new OrderDetail();
        Order order = new Order();
        Member member = new Member();
        member.setMemberCode("17781036104");
        order.setMember(member);
        //orderDetail.setOrder(order);
        Goods goods = new Goods();
        goods.setGoodsName("蒜爆肉");
        orderDetail.setGoods(goods);*/
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOdId(30L);
        List<OrderDetail> list = detailDao.findPage(orderDetail);
        System.out.println("列表长度"+list.size());
    }

    @Test
    @Ignore
    public void insert(){
        List<OrderDetail> orderList = new ArrayList<>();
        OrderDetail detail1 = new OrderDetail();
        detail1.setCount(2);
        Goods goods = new Goods();
        goods.setGoodsId(23);
        detail1.setGoods(goods);
        Order order = new Order();
        order.setOrderId(2L);
        detail1.setOrder(order);
        detail1.setStatus(0);
        detail1.setCreateTime(new Date());

        OrderDetail detail2 = new OrderDetail();
        detail2.setCount(1);
        goods = new Goods();
        goods.setGoodsId(24);
        detail2.setGoods(goods);
        detail2.setOrder(order);
        detail2.setStatus(0);
        detail2.setCreateTime(new Date());

        orderList.add(detail1);
        orderList.add(detail2);
        System.out.println(orderList.toString());
        detailDao.insert(orderList);
    }

    @Test
    @Ignore
    public void deleteByIds(){
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        System.out.println(detailDao.deleteByIds(idList));
    }

    @Test
    @Ignore
    public void update(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOdId(1L);
        Goods goods = new Goods();
        goods.setGoodsId(4);
        orderDetail.setGoods(goods);
        detailDao.update(orderDetail);
    }

    @Test
    @Ignore
    public void countPriceByOdIds(){
        String strIds = "17,18,19,20,";
        List<Long> idList = SplitIdsUtil.splitStrIds(strIds);
        System.out.println(idList.toString());
        System.out.println(detailDao.countPriceByOdIds(idList));
    };
}