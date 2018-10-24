package com.lzy.liujing.restaurant.controller.clientController;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Desk;
import com.lzy.liujing.restaurant.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guest")
public class ClientDeskController{
    /**
     * 客户端桌位接口
     */
    @Autowired
    private DeskService deskService;
    @GetMapping("/desklist.html")
    private String deskList(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        CustomPageInfo<Desk> customPageInfo = new CustomPageInfo<>();
        customPageInfo.setPageSize(15);
        customPageInfo.setPageNum(pageNum);
        model.addAttribute("page",deskService.findPage(customPageInfo));
        return "/client/deskPage";
    }

}
