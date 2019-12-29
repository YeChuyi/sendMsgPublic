package com.ycy.sendmessage.service.Impl;

import com.ycy.sendmessage.bean.MessageEnum;
import com.ycy.sendmessage.service.sendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * yechuyi
 * 2019-12-27
 */
@Service
@Slf4j
public class sendMsgServiceImpl implements sendMsgService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 发送文字验证码
     * @param phone 13700000000
     * @param content 你好，你的验证码是%s，五分钟为效。
     * @return
     */
    @Override
    public synchronized String send(String phone, String content) {
        String redisKey =   phone + MessageEnum.RedisTypeEnum.PHONE_KEY.code();
        //TODO 判断是否已存在redis
        //存在
         String phoneKey = redisTemplate.opsForValue().get(redisKey);
         if(StringUtils.isNotBlank(phoneKey)){
            return "每分钟只能发送一次短信";
         }else {
             int intFlag = (int) (Math.random() * 1000000);// UUID生成验证码
             //TODO 发送验证码存储于redis中
             //存储手机号码
             redisTemplate.opsForValue().set(redisKey, String.valueOf(intFlag), 60, TimeUnit.SECONDS);
             //验证码存储五分钟，当验证成功后失效
             String codeKey = phone + MessageEnum.RedisTypeEnum.CODE_KEY.code();
             redisTemplate.opsForValue().set(codeKey, String.valueOf(intFlag), 5 * 60, TimeUnit.SECONDS);
             return callSmsSupplier(phone,MessageEnum.MessageContent.VERIFI_MESSAGE.format(String.valueOf(intFlag)));
         }
    }


    /**
     * 校验手机验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    @Override
    public String check(String phone, String code) {
        String codeKey = phone + MessageEnum.RedisTypeEnum.CODE_KEY.code();
        String phoneKey = phone + MessageEnum.RedisTypeEnum.PHONE_KEY.code();
        //验证手机验证码是否正确
        String codeValue = redisTemplate.opsForValue().get(codeKey);
        if(StringUtils.equals(codeValue,code)){
            //删除验证码五分钟
            redisTemplate.delete(codeKey);
            //删除手机一分钟请求
            redisTemplate.delete(phoneKey);
            return "验证成功！";
        }else {
            return "验证码输入有误，请重新输入！";
        }

    }


    /**
     * 调用供应商发送短信。  不需要实现
     * @param phone
     * @param content
     */
    @Override
    public String callSmsSupplier(String phone, String content) {
      return content;
    }
}
