package com.lzy.liujing.restaurant.service;

import com.lzy.liujing.restaurant.entity.MemberCategory;

import java.util.List;

public interface MemberCategoryService {
    /**
     * 查询所有会员类型
     * @return
     */
    List<MemberCategory> findAll();

    /**
     * 根据会员id查找会员类型信息
     * @param id
     * @return
     */
    MemberCategory findById(Long id);

    /**
     *添加会员类型
     * @param memberCategory
     */
    void insert(MemberCategory memberCategory);

    /**
     * 修改会员类型信息
     * @param memberCategory
     */
    void update(MemberCategory memberCategory);

    /**
     * 根据会员类型id删除类型
     * @param strIds
     */
    void deleteByIds(String strIds);
}
