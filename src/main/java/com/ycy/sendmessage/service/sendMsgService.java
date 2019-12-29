package com.ycy.sendmessage.service;

/**
 * yechuyi
 * 2019-12-27  20:00
 */
public interface sendMsgService {
    /**
     * 发送文字验证码
     * @param phone 13700000000
     * @param content 你好，你的验证码是%s，五分钟为效。
     * @return
     */
    public String send(String phone,String content);

    /**
     * 校验手机验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    public String check(String phone,String code);

    /**
     * 调用供应商发送短信。  不需要实现
     * @param phone
     * @param content
     */
    public String callSmsSupplier(String phone,String content);

}
