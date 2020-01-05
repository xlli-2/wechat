package com.henu.wechat;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.henu.wechat.bean.BaseMessage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication()
@MapperScan("com.henu.wechat.dao")
public class WechatApplication {

    public static void main(String[] args) {

        SpringApplication.run(WechatApplication.class, args);
    }

//    @Bean
//    public BaseMessage baseMessage(){
//        return new BaseMessage();
//    }

}
