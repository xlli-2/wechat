package com.henu.wechat.dao;

import com.henu.wechat.entity.PullUser;

public interface PullUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PullUser record);

    int insertSelective(PullUser record);

    PullUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PullUser record);

    int updateByPrimaryKey(PullUser record);
}