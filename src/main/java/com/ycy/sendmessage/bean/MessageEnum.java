package com.ycy.sendmessage.bean;

import java.text.MessageFormat;

/**
 * 枚举类
 */
public  class MessageEnum {

    public enum MessageContent {

        VERIFI_MESSAGE("你好，你的验证码是{0}，五分钟内有效！");

        private String apiUrl;

        private MessageContent(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String format(Object... params) {
            return MessageFormat.format(this.apiUrl, params);
        }
    }

    public enum RedisTypeEnum{
        PHONE_KEY("_SMS_PHONE"),
        CODE_KEY("_VERIFY_CODE");
        private String code;

        RedisTypeEnum(String code) {
            this.code = code;
        }

        public String code() {
            return this.code;
        }


    }
}
;