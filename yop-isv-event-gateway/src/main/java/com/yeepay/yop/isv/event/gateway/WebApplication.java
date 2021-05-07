package com.yeepay.yop.isv.event.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * title: WebApplication<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-06-13 15:33
 */
@Configuration(proxyBeanMethods = false)
@SpringBootApplication(scanBasePackages = "com.yeepay.yop.isv.event.gateway")
@ServletComponentScan("com.yeepay.yop.isv.event.gateway")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
