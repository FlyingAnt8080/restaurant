package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:23:31
 */
@Mapper
public interface SysMenuDao extends tk.mybatis.mapper.common.Mapper<SysMenu>{
    SysMenu findById(Long menuId);

    /**
     * 根据用户查询菜单
     * @param sysMenu
     * @return
     */
    List<SysMenu> findList(SysMenu sysMenu);
}
