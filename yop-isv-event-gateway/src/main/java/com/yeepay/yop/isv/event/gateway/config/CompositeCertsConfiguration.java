/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.gateway.config;

import com.yeepay.yop.isv.event.gateway.cert.CompositeCertLocator;
import com.yeepay.yop.isv.event.sdk.cert.CertLocator;
import com.yeepay.yop.isv.event.sdk.cert.ConfigFileCertLocator;
import com.yeepay.yop.isv.event.sdk.resolver.impl.EventRequestResolver;
import com.yeepay.yop.isv.event.sdk.resolver.impl.NotifyRequestResolver;
import com.yeepay.yop.isv.event.sdk.resolver.param.NotifyRequestFormParamResolver;
import com.yeepay.yop.isv.event.sdk.resolver.param.NotifyRequestJsonParamResolver;
import com.yeepay.yop.isv.event.sdk.resolver.param.NotifyRequestParamResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.CONTENT_TYPE_FORM;
import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.CONTENT_TYPE_JSON;

/**
 * title: 基于组合的配置类<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/11 下午4:42
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "yop.certs.locator", matchIfMissing = false, havingValue = "composite")
@AutoConfigureAfter(GatewayAutoConfiguration.class)
public class CompositeCertsConfiguration {

    @Bean
    public CompositeCertLocator compositeCertLocator(ConfigFileCertLocator configFileCertLocator) {
        return new CompositeCertLocator(configFileCertLocator);
    }

    @Bean
    public EventRequestResolver eventRequestResolver(@Qualifier("compositeCertLocator") CertLocator certLocator) {
        EventRequestResolver eventRequestResolver = new EventRequestResolver();
        eventRequestResolver.setCertLocator(certLocator);
        return eventRequestResolver;
    }

    // Notify - RequestResolver

    @Bean
    public NotifyRequestResolver notifyRequestResolver(@Qualifier("compositeCertLocator") CertLocator certLocator,
                                                       NotifyRequestJsonParamResolver notifyRequestJsonParamResolver,
                                                       NotifyRequestFormParamResolver notifyRequestFormParamResolver) {
        Map<String, NotifyRequestParamResolver> paramResolverMap = new HashMap<>();
        paramResolverMap.put(CONTENT_TYPE_JSON, notifyRequestJsonParamResolver);
        paramResolverMap.put(CONTENT_TYPE_FORM, notifyRequestFormParamResolver);
        return new NotifyRequestResolver(paramResolverMap, certLocator);
    }

}
