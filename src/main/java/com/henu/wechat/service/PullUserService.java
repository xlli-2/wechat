package com.henu.wechat.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.henu.wechat.entity.PullUser;
import com.henu.wechat.dao.PullUserMapper;
@Service
public class PullUserService{

    @Resource
    private PullUserMapper pullUserMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return pullUserMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(PullUser record) {
        return pullUserMapper.insert(record);
    }

    
    public int insertSelective(PullUser record) {
        return pullUserMapper.insertSelective(record);
    }

    
    public PullUser selectByPrimaryKey(Integer id) {
        return pullUserMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(PullUser record) {
        return pullUserMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(PullUser record) {
        return pullUserMapper.updateByPrimaryKey(record);
    }

}
