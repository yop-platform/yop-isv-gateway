/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.sdk.resolver.param;

import com.fasterxml.jackson.databind.JavaType;
import com.yeepay.yop.isv.event.sdk.dto.DecryptParamDTO;
import com.yeepay.yop.isv.event.sdk.support.JsonMapper;
import com.yeepay.yop.sdk.security.CertTypeEnum;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.SM4_GCM_ALG;

/**
 * title: 老商户通知请求JSON参数解析器<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2021/4/21 17:45
 */
public class NotifyRequestJsonParamResolver implements NotifyRequestParamResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyRequestJsonParamResolver.class);

    private JsonMapper jsonMapper;

    private JavaType mapType;

    public NotifyRequestJsonParamResolver(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
        this.mapType = jsonMapper.contructMapType(HashMap.class, String.class, String.class);
    }

    @Override
    public DecryptParamDTO resolve(HttpServletRequest req) {
        DecryptParamDTO decryptParamDTO = new DecryptParamDTO();
        try {
            String json = IOUtils.toString(req.getReader());
            Map<String, String> params = jsonMapper.fromJson(json, mapType);
            String algorithm = params.get("algorithm");
            decryptParamDTO.setAppId(params.get("customerIdentification"));
            if (StringUtils.isNotEmpty(algorithm) && SM4_GCM_ALG.equals(algorithm)) {
                decryptParamDTO.setCertType(CertTypeEnum.SM4);
                decryptParamDTO.setCipher(params.get("cipherText"));
                decryptParamDTO.setOnce(params.get("once"));
                decryptParamDTO.setAssociatedData(params.get("associatedData"));
            } else {
                decryptParamDTO.setCipher(params.get("response"));
            }
            return decryptParamDTO;
        } catch (Exception e) {
            LOGGER.error("error when handle yop notify of json, ", e);
            return decryptParamDTO;
        }
    }

}
