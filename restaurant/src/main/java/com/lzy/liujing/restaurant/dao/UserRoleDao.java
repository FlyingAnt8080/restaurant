package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {
    int insert(UserRole userRole);
    int update(UserRole userRole);
}
