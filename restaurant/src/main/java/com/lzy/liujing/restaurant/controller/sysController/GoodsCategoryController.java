package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.entity.Member;
import com.lzy.liujing.restaurant.entity.GoodsCategory;
import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.service.GoodsCategoryService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/goodscategory")
public class GoodsCategoryController{
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/list.html")
    public String memberList(){
        return "goodscategory/list";
    }


    @GetMapping("/add.html")
    public String addMember(){
        return "goodscategory/add";
    }

    @PostMapping("/add.do")
    @ResponseBody
    public Result<Member> addMember(GoodsCategory GoodsCategory){
        goodsCategoryService.insert(GoodsCategory);
        return ResultUtil.success();
    }

    @GetMapping("/edit.html/{mcId}")
    public String editMember(@PathVariable("mcId") Long mcId, Model model){
        model.addAttribute("goodsCategory",goodsCategoryService.findById(mcId));
        return "goodscategory/edit";
    }

    @PostMapping("/edit.do")
    @ResponseBody
    public Result<Member> editMember(GoodsCategory category){
        goodsCategoryService.update(category);
        return ResultUtil.success();
    }

    @PostMapping("/del.do")
    @ResponseBody
    public Result<GoodsCategory> deleteMember(@RequestParam String ids){
        goodsCategoryService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 菜品类型
     * @return
     */
    @PostMapping("/list.do")
    @ResponseBody
    public Result<List<GoodsCategory>> memberListData(){
        List<GoodsCategory> categoryList = goodsCategoryService.findAll();
        Result<List<GoodsCategory>> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(categoryList);
        result.setCount((long) categoryList.size());
        return result;
    }
}
