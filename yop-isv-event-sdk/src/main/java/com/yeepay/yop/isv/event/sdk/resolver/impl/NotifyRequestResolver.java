/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.sdk.resolver.impl;

import com.yeepay.yop.isv.event.sdk.cert.CertLocator;
import com.yeepay.yop.isv.event.sdk.decrypt.YopDecryptorFactory;
import com.yeepay.yop.isv.event.sdk.dto.DecryptParamDTO;
import com.yeepay.yop.isv.event.sdk.dto.YopEvent;
import com.yeepay.yop.isv.event.sdk.exception.YopSignException;
import com.yeepay.yop.isv.event.sdk.resolver.RequestResolver;
import com.yeepay.yop.isv.event.sdk.resolver.param.NotifyRequestParamResolver;
import com.yeepay.yop.sdk.security.CertTypeEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.*;

/**
 * title: 老商户通知请求解析器<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/6 16:26
 */
public class NotifyRequestResolver implements RequestResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyRequestResolver.class);

    private Map<String, NotifyRequestParamResolver> paramResolverMap;
    private CertLocator certLocator;

    public NotifyRequestResolver(Map<String, NotifyRequestParamResolver> paramResolverMap,
                                 CertLocator certLocator) {
        this.paramResolverMap = paramResolverMap;
        this.certLocator = certLocator;
    }

    @Override
    public YopEvent resolve(HttpServletRequest req) {
        final String contentTypeStr = req.getContentType();
        String contentType = StringUtils.startsWith(contentTypeStr, CONTENT_TYPE_JSON) ? CONTENT_TYPE_JSON : CONTENT_TYPE_FORM;
        DecryptParamDTO decryptParamDTO = paramResolverMap.get(contentType).resolve(req);
        LOGGER.info("receive yop notify message:\nappId:{}, \ncontent:{}", decryptParamDTO.getAppId(), decryptParamDTO.getCipher());

        String eventType = req.getRequestURI();
        String requestBody = null;
        try {
            CertTypeEnum certType = ObjectUtils.defaultIfNull(decryptParamDTO.getCertType(), resolveCertType(decryptParamDTO.getCipher()));
            if (null != certType) {
                requestBody = YopDecryptorFactory.getDecryptor(certType).decrypt(decryptParamDTO, certLocator);
            } else {
                LOGGER.warn("only rsa and sm4 supported, so leave it alone");
                requestBody = decryptParamDTO.getCipher();
            }
        } catch (YopSignException e) {
            LOGGER.warn("decrypt msg fail,", e);
        }

        final YopEvent.Builder builder = YopEvent.Builder.builder()
                .withAppId(decryptParamDTO.getAppId())
                .withCreateTime(new Date())
                .withEventId(req.getHeader(HEADER_REQUEST_ID))
                .withEventType(eventType)
                .withBizData(requestBody);
        return builder.build();
    }

    private CertTypeEnum resolveCertType(String content) {
        String[] args = content.split("\\$");
        if (args.length == 4 && content.endsWith("$AES$SHA256")) {
            return CertTypeEnum.RSA2048;
        } else {
            return null;
        }
    }

}
