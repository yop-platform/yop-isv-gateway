/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.gateway.cert;

import com.yeepay.yop.isv.event.sdk.cert.CertLocator;
import com.yeepay.yop.sdk.config.provider.file.YopCertConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

/**
 * title: 证书定位器的组合<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/9 下午12:43
 */
public class CompositeCertLocator implements CertLocator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeCertLocator.class);

    private CertLocator delegate;

    public CompositeCertLocator(CertLocator certLocator) {
        this.delegate = certLocator;
    }

    @Override
    public PublicKey getYopPublicKey() {
        // 一般不需要重写
        // 直接沿用从配置文件或者常量类中读取 YOP 平台公钥
        return delegate.getYopPublicKey();
    }

    @Override
    public List<PrivateKey> getIsvPrivateKey(String appId) {
        try {
            // TODO 从您需要的地方加载
            return delegate.getIsvPrivateKey(appId);
        } catch (Exception e) {
            LOGGER.warn("Load isv cert fail, appId:" + appId, e);
            throw e;
        }
    }

    @Override
    public List<YopCertConfig> getIsvEncryptKeys(String appId) {
        try {
            // TODO 从您需要的地方加载
            return delegate.getIsvEncryptKeys(appId);
        } catch (Exception e) {
            LOGGER.warn("Load isv encrypt keys fail, appId:" + appId, e);
            throw e;
        }
    }

}
