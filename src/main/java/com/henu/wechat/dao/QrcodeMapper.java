package com.henu.wechat.dao;
import org.apache.ibatis.annotations.Param;

import com.henu.wechat.entity.Qrcode;

public interface QrcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Qrcode record);

    int insertSelective(Qrcode record);

    Qrcode selectByPrimaryKey(Integer id);

    Qrcode selectOneByOpenid(@Param("openid")String openid);


    int updateByPrimaryKeySelective(Qrcode record);

    int updateByPrimaryKey(Qrcode record);
}