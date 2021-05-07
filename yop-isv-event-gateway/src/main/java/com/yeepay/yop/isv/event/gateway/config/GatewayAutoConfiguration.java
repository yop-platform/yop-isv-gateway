/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.gateway.config;

import com.yeepay.yop.isv.event.sdk.cert.ConfigFileCertLocator;
import com.yeepay.yop.isv.event.sdk.handler.YopEventHandler;
import com.yeepay.yop.isv.event.sdk.handler.YopEventHandlerFactory;
import com.yeepay.yop.isv.event.sdk.resolver.param.NotifyRequestFormParamResolver;
import com.yeepay.yop.isv.event.sdk.resolver.param.NotifyRequestJsonParamResolver;
import com.yeepay.yop.isv.event.sdk.support.JsonMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * title: 网关自动配置类<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/11 下午4:42
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
public class GatewayAutoConfiguration {

    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper.defaultMapper();
    }

    @Bean
    public YopConfig yopIsvGatewayConfig() {
        return new YopConfig();
    }

    @Bean
    public ConfigFileCertLocator configFileCertLocator(YopConfig yopConfig) {
        ConfigFileCertLocator certLocator = new ConfigFileCertLocator();
        certLocator.setYopPublicKey(yopConfig.getYopPublicKey());
        certLocator.setIsvPrivateKeyMap(yopConfig.getIsvPrivateKeyMap());
        certLocator.setIsvEncryptKeys(yopConfig.getIsvEncryptKeys());
        return certLocator;
    }

    @Bean
    public YopEventHandlerFactory yopEventHandlerFactory(List<YopEventHandler> handlers) {
        for (YopEventHandler handler : handlers) {
            YopEventHandlerFactory.register(handler.getEventType(), handler);
        }
        return null;
    }

    // Notify - ParamResolver

    @Bean
    public NotifyRequestJsonParamResolver notifyRequestJsonParamResolver(JsonMapper jsonMapper) {
        return new NotifyRequestJsonParamResolver(jsonMapper);
    }

    @Bean
    public NotifyRequestFormParamResolver notifyRequestFormParamResolver() {
        return new NotifyRequestFormParamResolver();
    }

}
