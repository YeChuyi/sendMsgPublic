package com.ycy.sendmessage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yechuyi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    public String name;
    public String version;
    public boolean checkAccessToken;
    public boolean autoToken;

    //下载路径
    public String downloadPath;
    //上传路径
    public String uploadPath;
}
