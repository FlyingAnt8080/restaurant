package com.lzy.liujing.restaurant.dao;

import com.lzy.liujing.restaurant.entity.Desk;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DeskDao extends tk.mybatis.mapper.common.Mapper<Desk>{
    List<Desk> findPage(Desk desk);
    Desk findById(Integer deskId);
    Desk findByDeskCode(Desk desk);
    int insert(Desk desk);
    int update(Desk desk);
    int deleteByIds(List<Integer> idList);
}
