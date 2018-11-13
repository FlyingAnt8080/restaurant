package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Goods;

import java.util.List;

public interface GoodsService {
    /**
     * 分页查询以及分页条件查询
     *
     * @param pageInfo
     * @return
     */
    CustomPageInfo<Goods> findPage(CustomPageInfo<Goods> pageInfo);

    /**
     * 根据goodsId查询
     *
     * @param goodsId
     * @return
     */
    Goods findById(Integer goodsId);

    /**
     * 添加菜品
     *
     * @param goods
     */
    void insert(Goods goods);

    /**
     * 修改菜品
     *
     * @param goods
     */
    void update(Goods goods);

    /**
     * 根据id删除菜品
     *
     * @param strIds
     */
    void deleteByIds(String strIds);
}
