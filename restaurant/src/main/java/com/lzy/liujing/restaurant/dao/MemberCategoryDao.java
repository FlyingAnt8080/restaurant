package com.lzy.liujing.restaurant.dao;


import com.lzy.liujing.restaurant.entity.MemberCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/1
 * Time:23:36
 */
@Mapper
public interface MemberCategoryDao extends tk.mybatis.mapper.common.Mapper<MemberCategory>{
    List<MemberCategory> findAll();
    MemberCategory findById(Long id);
    int insert(MemberCategory memberCategory);
    int update(MemberCategory memberCategory);
    int deleteByIds(List<Long> idList);
    MemberCategory findByMcName(MemberCategory memberCategory);
}
