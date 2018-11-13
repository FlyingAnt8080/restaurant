package com.lzy.liujing.restaurant.dao;

import com.github.pagehelper.PageHelper;
import com.lzy.liujing.restaurant.entity.Order;
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
public class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;
    @Test
    @Ignore
    public void insert() {
        Order order = new Order();
        order.setDeskCode("13");
        order.setOrderCode("2018-1031-1632-100");
        order.setTotalCost(130.2);
        order.setTotalPrice(268.6);
        order.setPeopleNum(6);
        order.setCreateTime(new Date());
        orderDao.insert(order);
    }

    @Test
    @Ignore
    public void findPage() {
        PageHelper.startPage(1,10);
        Order order = new Order();
       /* orderDao.findPage(order);*/
    }

    @Test
    @Ignore
    public void findById() {
        System.out.println(orderDao.findById(1L).toString());
    }

    @Test
    @Ignore
    public void deleteByIds() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        orderDao.deleteByIds(list);
    }

    @Test
    @Ignore
    public void update() {
        Order order = new Order();
        order.setTotalCost(89D);
        order.setTotalPrice(231D);
        order.setOrderId(1L);
        orderDao.update(order);
    }
}