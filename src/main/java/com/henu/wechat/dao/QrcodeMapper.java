package com.henu.wechat.dao;

import com.henu.wechat.entity.Qrcode;

public interface QrcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Qrcode record);

    int insertSelective(Qrcode record);

    Qrcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Qrcode record);

    int updateByPrimaryKey(Qrcode record);
}