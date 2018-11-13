package com.lzy.liujing.restaurant.service;


import com.lzy.liujing.restaurant.entity.GoodsCategory;

import java.util.List;

/**
 * 菜品类型管理
 */
public interface GoodsCategoryService {
    /**
     * 查询所有菜品类型
     *
     * @return
     */
    List<GoodsCategory> findAll();

    /**
     * 根据菜品类型id查找菜品类型信息
     *
     * @param id
     * @return
     */
    GoodsCategory findById(Long id);

    /**
     * 添加菜品类型
     *
     * @param category
     */
    void insert(GoodsCategory category);

    /**
     * 修改菜品类型信息
     *
     * @param category
     */
    void update(GoodsCategory category);

    /**
     * 根据会员类型id删除类型
     *
     * @param strIds
     */
    void deleteByIds(String strIds);
}
