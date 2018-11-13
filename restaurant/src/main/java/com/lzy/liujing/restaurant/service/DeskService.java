package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Desk;

import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/2
 * Time:15:09
 *
 * 桌位管理业务逻辑
 */

public interface DeskService {
    /**
     * 分页查询桌位(可以加多条件)
     * @return
     */
    CustomPageInfo<Desk> findPage(CustomPageInfo<Desk> pageInfo);

    /**
     * 根据id查找桌位
     * @param id
     * @return
     */
    Desk findById(Integer id);

    /**
     *添加桌位
     * @param desk
     */
    void insert(Desk desk);

    /**
     * 修改桌位信息
     * @param desk
     */
    void update(Desk desk);

    /**
     * 根据桌位id删除桌位
     * @param strIds
     */
    void deleteByIds(String strIds);

    /**
     * 根据桌号查询
     * @param desk
     * @return
     */
    Desk findByDeskCode(Desk desk);

    /**
     * 桌位登录
     * @param desk
     */
    void login(Desk desk);

    /**
     * 桌位退出登录
     * @param desk
     */
    void logout(Desk desk);
}
