package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.*;
import com.lzy.liujing.restaurant.service.OrderDetailService;
import com.lzy.liujing.restaurant.service.OrderService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 订单操作接口
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/list.html")
    public String orderList(){
        return "/order/list";
    }

    @PostMapping("/list.do")
    @ResponseBody
    public Result<Order> orderList(CustomPageInfo<Order> pageInfo,Order order){
        //订单是未完成状态的，（以付款、已完成制菜的）是已完成订单即交易记录
        order.setOverStatus(0);
        pageInfo.setT(order);
        pageInfo.setOrderBy("ASC");
       CustomPageInfo<Order> resultInfo =  orderService.findPage(pageInfo);
        return ResultUtil.success(resultInfo.getList(),resultInfo.getTotal());
    }

    @GetMapping("/viewdetail.html/{orderId}")
    public String viewOrderDetail(@PathVariable(value = "orderId") Long orderId, Model model){
        model.addAttribute("orderId",orderId);
        return "order/orderDetail";
    }

    @GetMapping("/viewdetail.do/{orderId}")
    @ResponseBody
    public Result<OrderDetail> viewOrderDetail(CustomPageInfo pageInfo,@PathVariable(value = "orderId") Long orderId){
       OrderDetail detail = new OrderDetail();
       Order order = new Order();
       order.setOrderId(orderId);
       detail.setOrder(order);
       pageInfo.setT(detail);
       CustomPageInfo<OrderDetail> resultInfo = orderDetailService.findPage(pageInfo);
       return ResultUtil.success(resultInfo.getList(),resultInfo.getTotal());
    }

    @PostMapping("/del.do")
    @ResponseBody
    public Result<Order> deleteByIds(@RequestParam("ids") String ids){
        orderService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PostMapping("/deldetail.do")
    @ResponseBody
    public Result<OrderDetail> deleteByOrderDetailIds(@RequestParam("ids") String ids){
        orderDetailService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 根据桌号查询订单明细
     * @param deskCode
     * @return
     */
    @RequestMapping("/findDetail.do")
    @ResponseBody
    public Result<OrderDetail> findOrderDetailByDeskCode(@RequestParam("deskCode") String deskCode){
      List<OrderDetail> resultList =  orderDetailService.findOrderDetailsByDeskCode(deskCode);
        return ResultUtil.success(resultList,new Long(resultList.size()));
    }

    /**
     * 结账接口
     * @return
     */
    @PostMapping("/settleAccounts.do")
    @ResponseBody
    public Result<Order> settleAccounts(@RequestBody Order order, HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        //将当前收银员保存到订单中
        order.setCashier(user);
        orderService.settleAccounts(order);
        return ResultUtil.success();
    }
}
