package com.lzy.liujing.restaurant.utils;

import com.lzy.liujing.restaurant.entity.SysMenu;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/3
 * Time:23:42
 */

public class MenuUtil {
    /**
     * 获取子菜单
     * @param menuId
     * @param rootMenu
     * @return
     */
    public static List<SysMenu> getChild(Long menuId,List<SysMenu> rootMenu){
        List<SysMenu> childMenu = new ArrayList<>();//存放直接子菜单
        /**
         * 查找直接子菜单
         */
        for(SysMenu menu:rootMenu){
            if(menu.getParentId()!=0&&menu.getParentId()==menuId){
               childMenu.add(menu);
            }
        }
        //按权重排序
        sortByWeight(childMenu);
        if(childMenu.size()==0){
            return null;
        }
        /**
         * 遍历子菜的直接子菜单
         */
        for (SysMenu menu:childMenu){
            //这个判断的意思是 如果url 不为空说明是最后一个节点(叶子节点)
           /* if(StringUtil.isEmpty(menu.getMenuHref())){
                menu.setChildMenu(getChild(menu.getMenuId(),rootMenu));
            }*/
            menu.setChildMenus(getChild(menu.getMenuId(),rootMenu));
        }
        return childMenu;
    }

    /**
     *
     * @param rootMenu 所有菜单集合
     * @param enableIsShowFields  是否使用is_show 字段
     * @return
     */
    public static List<SysMenu> formatMenuList(List<SysMenu> rootMenu,boolean enableIsShowFields){
        List<SysMenu> formatMenuList = new ArrayList<>();
        /**
         * 判断是否使用is_show字段
         * is_show=0 代表不显示菜单  is_show=1代表显示
         */
        if(enableIsShowFields){
            List<SysMenu> clearMenuList = new ArrayList<>();
            System.out.println(rootMenu.size());
            for(SysMenu menu:rootMenu){
                //0 代表不显示 1代表显示
                if(menu.getIsShow()==1){
                    clearMenuList.add(menu);
                }
            }
            rootMenu = clearMenuList;
            System.out.println(rootMenu.size());
        }
        /**
         * 找到所有的一级子菜单
         */
        for(SysMenu menu:rootMenu){
            if(menu.getParentId()==0){
                formatMenuList.add(menu);
            }
        }
        //按权重排序
        sortByWeight(formatMenuList);
        /**
         * 一级菜单后开始递归子菜单
         */
        for(SysMenu menu:formatMenuList){
            menu.setChildMenus(getChild(menu.getMenuId(),rootMenu));
        }
        return formatMenuList;
    }

    /**
     * 运用选择排序
     * @param menuList
     */
    public static void sortByWeight(List<SysMenu> menuList){
        SysMenu tempMenu;
        for(int i=0;i<menuList.size();i++){
            for(int j=i+1;j<menuList.size();j++){
                if(menuList.get(i).getWeight()<menuList.get(j).getWeight()){
                    tempMenu = menuList.get(j);
                    //menuList.get(j) = menuList.get(i)  是错误的，集合不能直接赋值
                    menuList.set(j,menuList.get(i));
                    menuList.set(i,tempMenu);
                }
            }
        }
    }
}
