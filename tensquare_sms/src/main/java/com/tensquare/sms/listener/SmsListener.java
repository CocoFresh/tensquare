package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/16 17:45
 * @Description:
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code; //模板代码

    @Value("${aliyun.sms.sign_name}")
    private String sign_name; //签名


    @RabbitHandler()
    public void sendSms(Map<String, String> map) throws ClientException {
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");

        smsUtil.sendSms(mobile, template_code, sign_name, " {\"number\":\"" + checkcode + "\"}");
    }
}
