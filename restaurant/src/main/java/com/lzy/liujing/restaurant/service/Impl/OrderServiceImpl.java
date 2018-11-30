package com.lzy.liujing.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.dao.*;
import com.lzy.liujing.restaurant.entity.*;
import com.lzy.liujing.restaurant.exception.CustomException;
import com.lzy.liujing.restaurant.service.OrderService;
import com.lzy.liujing.restaurant.utils.OrderCodeUtil;
import com.lzy.liujing.restaurant.utils.SplitIdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberCategoryDao memberCategoryDao;
    @Autowired
    private DeskDao deskDao;
    /**
     * 添加订单
     *
     * @param order
     */
    @Override
    public String addOrder(Order order) {
        String orderCode = OrderCodeUtil.createOrderCode();
        //创建一个订单号
        order.setOrderCode(orderCode);
        order.setCreateTime(new Date());
        //此处返回该条记录的orderId到order对象中
        orderDao.insert(order);
        //设置订单orderId
        Order order1 = new Order();
        order1.setOrderId(order.getOrderId());
        //插入订单明细
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            order.getOrderDetails().get(i).setOrder(order1);
        }
        int effect = orderDetailDao.insert(order.getOrderDetails());
        //减库存,加销量
        for (OrderDetail detail:order.getOrderDetails()){
            updateStoreAndSold(detail.getGoods().getGoodsId(),detail.getCount());
        }
        if(effect<=0){
            throw new CustomException(ResultEnum.ADD_ORDER_FAIL);
        }
        return orderCode;
    }

    /**
     * 修改库存和销量
     * @param goodsId
     * @param count
     */
    public void updateStoreAndSold(Integer goodsId,int count){
        Goods oldGoods = goodsDao.findById(goodsId);
        Goods newGoods = new Goods();
        newGoods.setGoodsId(oldGoods.getGoodsId());
        newGoods.setStoreCount(oldGoods.getStoreCount()-count);
        newGoods.setSoldCount(oldGoods.getSoldCount()+count);
        //如果库存为0，则将商品设置成已售罄状态
        if(oldGoods.getStoreCount()==count){
            newGoods.setSoldState(1);
        }
        goodsDao.update(newGoods);
    }
    /**
     * 多条件分页查询订单
     * @param pageInfo
     * @return
     */
    @Override
    public CustomPageInfo<Order> findPage(CustomPageInfo<Order> pageInfo) {
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        orderDao.findPage(pageInfo);
        CustomPageInfo<Order> resultInfo = new CustomPageInfo<>(page);
        return resultInfo;
    }

    /**
     * 根据订单id查询订单
     *
     * @param orderId
     * @return
     */
    @Override
    public Order findById(Long orderId){
        return orderDao.findById(orderId);
    }

    /**
     * 根据订单id删除订单
     *
     * @param strIds
     */
    @Override
    public void deleteByIds(String strIds) {
       List<Long> idList =  SplitIdsUtil.splitStrIds(strIds);
       int effect = orderDao.deleteByIds(idList);
       if(effect<=0){
           throw new CustomException(ResultEnum.DEL_DB_FAIL);
       }
       orderDetailDao.deleteByOrderIds(idList);
    }

    /**
     * 修改订单
     *
     * @param order
     */
    @Override
    public void update(Order order){
        orderDao.update(order);
    }

    /**
     * 结账业务
     * @param order
     */
    @Override
    public void settleAccounts(Order order){
        OrderDetail orderDetail = new OrderDetail();
        Order conditionOrder = new Order();
        conditionOrder.setOrderCode(order.getOrderCode());
        orderDetail.setOrder(conditionOrder);
        List<OrderDetail> resultDetailList = orderDetailDao.findPage(orderDetail);
        //总成本
        double totalCost=0;
        //计算总成本
        for(OrderDetail detail:resultDetailList){
            //每个菜的成本乘以数量
            totalCost+=detail.getGoods().getCost()*detail.getCount();
        }
        //设置利润
        order.setTotalProfit(order.getMustPay()-totalCost);
        //设置总成本
        order.setTotalCost(totalCost);
        //设置为支付状态
        order.setPayStatus(1);
        /**
         * 判断该订单实付已上菜完毕，如果上菜完毕，则将订单设置成结束状态1
         */
        if(resultDetailList.get(0).getOrder().getFinishStatus()==1){
            order.setOverStatus(1);
            //将对应餐桌状态设置成'待清理'
            String currDeskCode = resultDetailList.get(0).getOrder().getDeskCode();
            Desk desk = new Desk();
            desk.setDeskCode(currDeskCode);
            desk.setIdleStatus(2);
            deskDao.update(desk);
        }
        int effect = orderDao.updateByOrderCode(order);
        /**
         *  增加会员的总消费额
         *  1先根据会员号查出会员信息
         *  2修改会员消费总金额
         */
        Member member = order.getMember();
        //查出该会员的信息
        Member queryMember = (memberDao.findPage(member)).get(0);
        Double currentTotalMoney = queryMember.getTotalMoney()+order.getMustPay();
        member.setTotalMoney(currentTotalMoney);
        /**
         * 判断会员需不需要升级
         * 查找当前会员消费总金额能达到的会员等级的id
         */
        Integer mcId = queryMember.getMemberCategory().getMcId();
        List<MemberCategory> categoryList = memberCategoryDao.findAll();
        for(MemberCategory category:categoryList){
            if(currentTotalMoney>=category.getAmount()){
                mcId = category.getMcId();
            }
        }

        MemberCategory memberCategory = new MemberCategory();
        memberCategory.setMcId(mcId);
        //根据会员id来修改会员信息
        member.setMemberCode(null);
        member.setMemberId(queryMember.getMemberId());
        member.setMemberCategory(memberCategory);
        memberDao.update(member);
        if(effect<=0){
            throw  new CustomException(ResultEnum.UPDATE_DB_FAIL);
        }
    }
}
