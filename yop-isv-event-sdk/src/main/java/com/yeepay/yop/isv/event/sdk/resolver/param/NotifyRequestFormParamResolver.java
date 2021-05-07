/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.sdk.resolver.param;

import com.yeepay.yop.isv.event.sdk.dto.DecryptParamDTO;
import com.yeepay.yop.sdk.security.CertTypeEnum;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.SM4_GCM_ALG;

/**
 * title: 老商户通知请求FORM参数解析器<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2021/4/21 17:45
 */
public class NotifyRequestFormParamResolver implements NotifyRequestParamResolver {

    @Override
    public DecryptParamDTO resolve(HttpServletRequest req) {
        String algorithm = req.getParameter("algorithm");
        DecryptParamDTO decryptParamDTO = new DecryptParamDTO();
        decryptParamDTO.setAppId(req.getParameter("customerIdentification"));
        if (StringUtils.isNotEmpty(algorithm) && SM4_GCM_ALG.equals(algorithm)) {
            decryptParamDTO.setCertType(CertTypeEnum.SM4);
            decryptParamDTO.setCipher(req.getParameter("cipherText"));
            decryptParamDTO.setOnce(req.getParameter("once"));
            decryptParamDTO.setAssociatedData(req.getParameter("associatedData"));
        } else {
            decryptParamDTO.setCipher(req.getParameter("response"));
        }
        return decryptParamDTO;
    }

}
