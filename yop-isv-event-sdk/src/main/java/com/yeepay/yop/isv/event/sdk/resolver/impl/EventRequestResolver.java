/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.sdk.resolver.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.yeepay.yop.isv.event.sdk.cert.CertLocator;
import com.yeepay.yop.isv.event.sdk.dto.YopEvent;
import com.yeepay.yop.isv.event.sdk.exception.YopHandleException;
import com.yeepay.yop.isv.event.sdk.exception.YopParamException;
import com.yeepay.yop.isv.event.sdk.exception.YopSignException;
import com.yeepay.yop.isv.event.sdk.resolver.RequestResolver;
import com.yeepay.yop.isv.event.sdk.support.HttpUtils;
import com.yeepay.yop.isv.event.sdk.support.RSA;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.*;

/**
 * title: 事件请求请求解析器<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/6 16:26
 */
public class EventRequestResolver implements RequestResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventRequestResolver.class);

    private static final Joiner HEADER_JOINER = Joiner.on('\n');

    private CertLocator certLocator;

    @Override
    public YopEvent resolve(HttpServletRequest req) throws IOException {
        LOGGER.debug("begin to handle yop event, req:{}", req);
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            Map<String, String> requestHeaders = HttpUtils.getHeaders(req);
            checkRequestHeaders(requestHeaders);

            String requestBody = HttpUtils.getBodyString(req);
            checkRequestBody(requestBody);

            //验签
            final boolean verifySign = rsaVerifySign(requestHeaders, requestBody, certLocator.getYopPublicKey());
            if (!verifySign) {
                throw new YopSignException("fail to verify sign.");
            }

            final String eventType = requestHeaders.get(HEADER_EVENT_TYPE);
            String appId = requestHeaders.get(HEADER_APP_ID);
            final YopEvent.Builder builder = YopEvent.Builder.builder()
                    .withAppId(appId)
                    .withCreateTime(new Date())
                    .withEventId(requestHeaders.get(HEADER_REQUEST_ID))
                    .withEventType(eventType)
                    .withBizData(requestBody);
            requestHeaders.forEach(builder::withMetaInfo);
            return builder.build();
        } finally {
            stopWatch.stop();
            LOGGER.debug("end to handle yop event, elapsedTime:{}ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
        }
    }

    private static void checkRequestHeaders(Map<String, String> requestHeaders) throws YopParamException {
        if (null == requestHeaders || requestHeaders.isEmpty()) {
            throw new YopParamException("missing headers");
        }
        for (String standardHeader : STANDARD_HEADERS) {
            if (!requestHeaders.containsKey(standardHeader)) {
                throw new YopParamException("missing header {0}", standardHeader);
            }
        }
    }

    private static void checkRequestBody(String requestBody) throws YopParamException {
        if (StringUtils.isBlank(requestBody)) {
            throw new YopParamException("missing body");
        }
    }

    private static boolean rsaVerifySign(Map<String, String> requestHeaders, String requestBody, PublicKey publicKey) {
        String sign = requestHeaders.get(HEADER_SIGN);
        String canonicalHeader = getCanonicalHeaders(requestHeaders);
        String digest = canonicalHeader + "\n" + requestBody;
        return RSA.verifySign(digest, sign, publicKey);
    }

    private static String getCanonicalHeaders(Map<String, String> requestHeaders) {
        if (requestHeaders.isEmpty()) {
            return "";
        }

        List<String> headerStrings = Lists.newArrayList();
        for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            String key = entry.getKey();
            if (signHeaders(key)) {
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                headerStrings.add(HttpUtils.normalize(key.trim().toLowerCase()) + ':' + HttpUtils.normalize(value.trim()));
            }
        }
        Collections.sort(headerStrings);
        return HEADER_JOINER.join(headerStrings);
    }

    private static boolean signHeaders(String headerName) {
        if (null != headerName) {
            return headerName.startsWith(STANDAED_HEADER_PREFIX) && !HEADER_SIGN.equals(headerName) && !HEADER_SIGN_TYPE.equals(headerName);
        }
        return false;
    }

    public void setCertLocator(CertLocator certLocator) {
        this.certLocator = certLocator;
    }

}
