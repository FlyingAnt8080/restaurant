package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.SysMenu;
import com.lzy.liujing.restaurant.utils.MenuUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/3
 * Time:22:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuDaoTest {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Test
    public void findById() {
    }

    @Test
    public void findList() {
        SysMenu sysMenu = new SysMenu();
        Set<String> roles = new HashSet<>();
        roles.add("经理");
        sysMenu.getCondition().put("list",roles);
        List<SysMenu> menuList = sysMenuDao.findList(sysMenu);
      /*  System.out.println(menus);*/
        System.out.println(menuList.size());
        List<SysMenu> formatMenuList = MenuUtil.formatMenuList(menuList,true);
        for(SysMenu menu:formatMenuList){
            System.out.println("----"+menu.getMenuName());
            for (SysMenu childMenu:menu.getChildMenus()){
                System.out.println("------------"+childMenu.getMenuName());
                if(childMenu.getChildMenus()!=null){
                    for(SysMenu child:childMenu.getChildMenus()){
                        System.out.println("------------------------"+child.getMenuName());
                    }
                }
            }
        }
    }
}