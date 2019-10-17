package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/16 14:29
 * @Description:
 */
@Component
@RabbitListener(queues = "itcast")
public class Customer1 {
    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("itcast：" + msg);
    }
}
