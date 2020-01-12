package com.henu.wechat.dao;

import com.henu.wechat.bean.MyTeam;
import com.henu.wechat.entity.PullUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PullUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PullUser record);

    int insertSelective(PullUser record);

    PullUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PullUser record);

    int updateByPrimaryKey(PullUser record);

    List<MyTeam> selectMyTeamByOpenid(@Param("openid") String openId);
}