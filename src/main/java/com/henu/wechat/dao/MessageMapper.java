package com.henu.wechat.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.henu.wechat.entity.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectByStatusOrderBySort();




}