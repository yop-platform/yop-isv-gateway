package com.yeepay.yop.isv.event.gateway.config;

import com.yeepay.yop.sdk.config.provider.file.YopCertConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * title: YOP 配置<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/9 上午11:19
 */
@ConfigurationProperties(prefix = "yop.certs")
public class YopConfig {

    private Map<String, List<String>> isvPrivateKeyMap;

    private String yopPublicKey;

    private List<YopCertConfig> isvEncryptKeys;

    public List<String> getIsvPrivateKey(String appId) {
        return isvPrivateKeyMap.get(appId);
    }

    public Map<String, List<String>> getIsvPrivateKeyMap() {
        return isvPrivateKeyMap;
    }

    public void setIsvPrivateKeyMap(Map<String, List<String>> isvPrivateKeyMap) {
        this.isvPrivateKeyMap = isvPrivateKeyMap;
    }

    public String getYopPublicKey() {
        return yopPublicKey;
    }

    public void setYopPublicKey(String yopPublicKey) {
        this.yopPublicKey = yopPublicKey;
    }

    public List<YopCertConfig> getIsvEncryptKeys() {
        return isvEncryptKeys;
    }

    public void setIsvEncryptKeys(List<YopCertConfig> isvEncryptKeys) {
        this.isvEncryptKeys = isvEncryptKeys;
    }
}
