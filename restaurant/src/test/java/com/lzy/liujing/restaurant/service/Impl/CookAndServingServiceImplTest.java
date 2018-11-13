package com.lzy.liujing.restaurant.service.Impl;

import com.lzy.liujing.restaurant.dao.OrderDetailDao;
import com.lzy.liujing.restaurant.entity.OrderDetail;
import com.lzy.liujing.restaurant.service.CookAndServingService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CookAndServingServiceImplTest {
    @Autowired
    private CookAndServingService cookAndServingService;
    @Autowired
    private OrderDetailDao orderDetailDao;

    /**
     * 测试制菜统筹算法
     */
    @Test
    @Ignore
    public void cookTaskManage() {
        List<Integer> statusList = new ArrayList<>();
        //查询正在制作的菜
        statusList.add(1);
        List<OrderDetail> resultCookTaskList = orderDetailDao.findPageByStatus(statusList);
        cookAndServingService.cookTaskManage(resultCookTaskList);
        //查询所有等待制作的
        statusList.clear();
        statusList.add(0);
        List<OrderDetail> cookingTaskList = orderDetailDao.findPageByStatus(statusList);
        cookAndServingService.cookTaskManage(cookingTaskList);
        resultCookTaskList.addAll(cookingTaskList);
    }
}