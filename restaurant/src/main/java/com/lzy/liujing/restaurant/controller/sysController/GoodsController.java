package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.*;
import com.lzy.liujing.restaurant.service.GoodsCategoryService;
import com.lzy.liujing.restaurant.service.GoodsService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController{
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/list.html")
    public String goodsList(Model model, HttpSession session){
        model.addAttribute("goodsCategoryList",goodsCategoryService.findAll());
        SysUser user = (SysUser) session.getAttribute("user");
        model.addAttribute("role",user.getRole());
        return "goods/list";
    }

    @PostMapping("/list.do")
    @ResponseBody
    public Result<Goods> goodsList(CustomPageInfo<Goods> pageInfo, Goods goods,GoodsCategory goodsCategory){
        goods.setGoodsCategory(goodsCategory);
        pageInfo.setT(goods);
        CustomPageInfo<Goods> resultPage = goodsService.findPage(pageInfo);
        return ResultUtil.success(resultPage.getList(),resultPage.getTotal());
    }

    @GetMapping("/add.html")
    public String addGoods(Model model){
        model.addAttribute("goodsCategoryList",goodsCategoryService.findAll());
        return "goods/add";
    }

    @PostMapping("/add.do")
    @ResponseBody
    public Result<Goods> addGoods(Goods goods,GoodsCategory goodsCategory){
        goods.setGoodsCategory(goodsCategory);
        goodsService.insert(goods);
        return ResultUtil.success();
    }

    @GetMapping("/edit.html/{goodsId}")
    public String editGoods(Model model, @PathVariable("goodsId") Integer goodsId){
        model.addAttribute("goodsCategoryList",goodsCategoryService.findAll());
        model.addAttribute("goods",goodsService.findById(goodsId));
        return "goods/edit";
    }

    @PostMapping("/edit.do")
    @ResponseBody
    public Result<Goods> editGoods(Goods goods,GoodsCategory goodsCategory){
        goods.setGoodsCategory(goodsCategory);
        goodsService.update(goods);
        return ResultUtil.success();
    }
    @PostMapping("/del.do")
    @ResponseBody
    public Result<Goods> delGoods(@RequestParam("ids") String ids){
        goodsService.deleteByIds(ids);
        return ResultUtil.success();
    }
}
