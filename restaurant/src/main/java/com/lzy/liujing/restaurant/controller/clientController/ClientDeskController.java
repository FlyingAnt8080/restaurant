package com.lzy.liujing.restaurant.controller.clientController;

import com.lzy.liujing.restaurant.entity.*;
import com.lzy.liujing.restaurant.service.DeskService;
import com.lzy.liujing.restaurant.service.GoodsCategoryService;
import com.lzy.liujing.restaurant.service.GoodsService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/guest")
public class ClientDeskController{

    @Autowired
    private DeskService deskService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    /**
     * 客户端桌位接口
     */
    @PostMapping("/login.do")
    @ResponseBody
    private Result deskLogin(Desk desk,HttpSession session){
        deskService.login(desk);
        session.setAttribute("deskCode",desk.getDeskCode());
        return ResultUtil.success();
    }

    @GetMapping("/logout.do/{deskCode}")
    private String deskLoginOut(@PathVariable("deskCode") String deskCode,HttpSession session){
        Desk desk = new Desk();
        desk.setDeskCode(deskCode);
        deskService.logout(desk);
        session.removeAttribute("deskCode");
        return "redirect:/guest/desklist.html";
    }

    @GetMapping("/desklist.html")
    private String deskList(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        CustomPageInfo<Desk> customPageInfo = new CustomPageInfo<>();
        customPageInfo.setPageSize(15);
        customPageInfo.setPageNum(pageNum);
        model.addAttribute("page",deskService.findPage(customPageInfo));
        return "/client/deskPage";
    }

    @GetMapping("/main.html")
    private String clientMain(Model model){
        model.addAttribute("categoryList",goodsCategoryService.findAll());
        return "/client/main";
    }

    @GetMapping("/goodspage.thml")
    private String goodsPage(){
        return "/client/goodsPage";
    }

    /**
     * 商品列表html页面
     * @param model
     * @return
     */
    @GetMapping("/goodslist.html/{categoryId}")
    public String goodsList(Model model,@PathVariable("categoryId") Integer categoryId){
        //0代表查询所有菜，即无条件查询
        if (categoryId==0){
            categoryId=null;
        }
        model.addAttribute("categoryId",categoryId);
        return "/client/goodsPage";
    }

    /**
     * 商品列表数据
     * @param pageInfo
     * @param goods
     * @param goodsCategory
     * @return
     */
    @PostMapping("/goodslist.do")
    @ResponseBody
    public Result<Goods> goodsList(CustomPageInfo<Goods> pageInfo, Goods goods, GoodsCategory goodsCategory){
        goods.setGoodsCategory(goodsCategory);
        goods.setPutawayStatus(1);
        pageInfo.setT(goods);
        CustomPageInfo<Goods> resultPage = goodsService.findPage(pageInfo);
        return ResultUtil.success(resultPage.getList(),resultPage.getTotal());
    }
}
