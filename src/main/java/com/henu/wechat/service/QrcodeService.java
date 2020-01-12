package com.henu.wechat.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.henu.wechat.entity.Qrcode;
import com.henu.wechat.dao.QrcodeMapper;
@Service
public class QrcodeService{

    @Resource
    private QrcodeMapper qrcodeMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return qrcodeMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Qrcode record) {
        return qrcodeMapper.insert(record);
    }

    
    public int insertSelective(Qrcode record) {
        return qrcodeMapper.insertSelective(record);
    }

    
    public Qrcode selectByPrimaryKey(Integer id) {
        return qrcodeMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Qrcode record) {
        return qrcodeMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Qrcode record) {
        return qrcodeMapper.updateByPrimaryKey(record);
    }

}
