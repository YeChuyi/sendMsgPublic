package com.ycy.sendmessage.sendmsg;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableSwaggerBootstrapUI
@ComponentScan(basePackages = "com.ycy.sendmessage")
public class SendmsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendmsgApplication.class, args);
    }

}
