package com.lzy.liujing.restaurant.controller.sysController;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Desk;
import com.lzy.liujing.restaurant.entity.Result;
import com.lzy.liujing.restaurant.entity.SysUser;
import com.lzy.liujing.restaurant.service.DeskService;
import com.lzy.liujing.restaurant.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/desk")
public class DeskController {
    @Autowired
    private DeskService deskService;

    @GetMapping("/list.html")
    private String deskList(HttpSession session,Model model){
        SysUser user = (SysUser) session.getAttribute("user");
        model.addAttribute("role",user.getRole());
        return "desk/list";
    }

    @PostMapping("/list.do")
    @ResponseBody
    private Result<Desk> deskList(CustomPageInfo<Desk> pageInfo,Desk desk){
        pageInfo.setT(desk);
        CustomPageInfo<Desk> resultInfo = deskService.findPage(pageInfo);
        return ResultUtil.success(resultInfo.getList(),resultInfo.getTotal());
    }

    @GetMapping("/add.html")
    private String addDesk(){
        return "desk/add";
    }

    @PostMapping("/add.do")
    @ResponseBody
    private Result<Desk> addDesk(Desk desk){
        deskService.insert(desk);
        return ResultUtil.success();
    }

    @GetMapping("/edit.html/{deskId}")
    private String editDesk(@PathVariable("deskId") Integer deskId, Model model){
        model.addAttribute("desk",deskService.findById(deskId));
        return "desk/edit";
    }

    @PostMapping("/edit.do")
    @ResponseBody
    private Result<Desk> editDesk(Desk desk){
        deskService.update(desk);
        return ResultUtil.success();
    }

    @PostMapping("/del.do")
    @ResponseBody
    private Result<Desk> delDesk(@RequestParam("ids") String strIds){
        deskService.deleteByIds(strIds);
        return ResultUtil.success();
    }
}
