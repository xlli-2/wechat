package com.henu.wechat.dao;

import com.henu.wechat.entity.WxUser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

public class WxUserMapperTest {
    private static WxUserMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(WxUserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/WxUserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(WxUserMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByOpenid() throws FileNotFoundException {
        WxUser wxUser = mapper.selectByOpenid("ogCiUjtouc4voxnRHfxigqYVej30");
        System.out.println(wxUser);
    }
}
