package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.*;
import com.lzy.liujing.restaurant.service.CookAndServingService;
import com.lzy.liujing.restaurant.service.OrderDetailService;
import com.lzy.liujing.restaurant.service.OrderService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.apache.catalina.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 制菜上菜管理网络接口
 */
@Controller
@RequestMapping("/service")
public class CookAndServingController{
    @Autowired
    private CookAndServingService cookAndServingService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 制菜任务列表HTML界面
     * @return
     */
    @GetMapping("/cookTask.html")
    @RequiresPermissions("cooking:view")
    public String cookTaskList(HttpSession session,Model model){
        SysUser user = (SysUser) session.getAttribute("user");
        model.addAttribute("role",user.getRole());
        return "cook/cookTaskList";
    }

    /**
     * 制菜任务列表数据接口
     * @param pageInfo
     * @return
     */
    @GetMapping("/cookTask.do")
    @RequiresPermissions("cooking:view")
    @ResponseBody
    public Result<OrderDetail> cookTaskList(CustomPageInfo<OrderDetail> pageInfo){
        List<OrderDetail> resultList = cookAndServingService.cookTaskFindPage(pageInfo);
        return ResultUtil.success(resultList,new Long((resultList.size())));
    }

    /**
     * 开始制菜修改状态接口
     * 获取前台传的数组，保存到List集合，必须添加value = "odIdList[]
     * @param odIdList
     * @return
     */
    @PostMapping("/startCook.do")
    @RequiresPermissions("cooking:edit")
    @ResponseBody
    public Result<OrderDetail> startCook(@RequestParam(value = "odIdList[]") List<Long> odIdList){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(1);
        orderDetail.setOdIdList(odIdList);
        cookAndServingService.updateStatusByOdIds(orderDetail);
        return ResultUtil.success();
    }

    /**
     * 完成制菜修改状态接口
     * 获取前台传的数组，保存到List集合，必须添加value = "odIdList[]
     * @param odIdList
     * @return
     */
    @PostMapping("/finishCook.do")
    @RequiresPermissions("cooking:edit")
    @ResponseBody
    public Result<OrderDetail> finishCook(@RequestParam(value = "odIdList[]") List<Long> odIdList){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(2);
        orderDetail.setOdIdList(odIdList);
        cookAndServingService.updateStatusByOdIds(orderDetail);
        return ResultUtil.success();
    }

    /**
     * 上菜任务列表HTML界面
     * @return
     */
    @GetMapping("/servingTask.html")
    @RequiresPermissions("serving:view")
    public String servingTaskList(HttpSession session,Model model){
        SysUser user = (SysUser) session.getAttribute("user");
        model.addAttribute("role",user.getRole());
        return "cook/servingTaskList";
    }

    /**
     * 上菜任务列表数据接口
     * @param pageInfo
     * @return
     */
    @GetMapping("/servingTask.do")
    @RequiresPermissions("serving:view")
    @ResponseBody
    public Result<OrderDetail> servingTask(CustomPageInfo<OrderDetail> pageInfo){
        CustomPageInfo<OrderDetail> resultInfo = cookAndServingService.servingTaskFindPage(pageInfo);
        return ResultUtil.success(resultInfo.getList(),resultInfo.getTotal());
    }

    /**
     * 完成上菜接口
     * @param orderDetail
     * @param order
     * @return
     */
    @GetMapping("/finishServing.do")
    @RequiresPermissions("serving:edit")
    @ResponseBody
    public Result<OrderDetail> finishServing(OrderDetail orderDetail,Order order){
        orderDetail.setOrder(order);
        cookAndServingService.finishServing(orderDetail);
        return ResultUtil.success();
    }

    /**
     * 桌位上菜情况列表HTML界面
     * @return
     */
    @GetMapping("/dssList.html")
    @RequiresPermissions("deskServing:view")
    public String deskServingStatusList(){
        return "/cook/deskServingStatus";
    }

    /**
     * 桌位上菜情况列表数据接口
     * @param pageInfo
     * @param order
     * @return
     */
    @PostMapping("/dssList.do")
    @RequiresPermissions("deskServing:view")
    @ResponseBody
    public Result<Order> deskServingStatusList(CustomPageInfo<Order> pageInfo,Order order){
        order.setOverStatus(0);
        pageInfo.setT(order);
        CustomPageInfo<Order> resultInfo =  orderService.findPage(pageInfo);
        return ResultUtil.success(resultInfo.getList(),resultInfo.getTotal());
    }

    /**
     * 桌位上菜详细情况HTML页面
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/ssd.html/{orderId}")
    @RequiresPermissions("deskServing:view")
    public String servingStatusDetail(@PathVariable(value = "orderId") Long orderId, Model model){
        model.addAttribute("orderId",orderId);
        return "cook/servingStatusDetail";
    }

    /**
     * 桌位上菜详细情况数据接口
     * @param pageInfo
     * @param orderId
     * @return
     */
    @GetMapping("/ssd.do/{orderId}")
    @RequiresPermissions("deskServing:view")
    @ResponseBody
    public Result<OrderDetail> servingStatusDetail(CustomPageInfo pageInfo,@PathVariable(value = "orderId") Long orderId){
        OrderDetail detail = new OrderDetail();
        Order order = new Order();
        order.setOrderId(orderId);
        detail.setOrder(order);
        pageInfo.setT(detail);
        CustomPageInfo<OrderDetail> resultInfo = orderDetailService.findPage(pageInfo);
        return ResultUtil.success(resultInfo.getList(),resultInfo.getTotal());
    }
}
