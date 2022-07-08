/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.gateway.handler;

import com.yeepay.yop.sdk.service.common.callback.YopCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/10 上午10:06
 */
@Component
public class MockCallbackHandler extends YopBaseCallbackHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockCallbackHandler.class);

    @Override
    public String getType() {
        return "/rest/v1.0/test/app-alias/create";
    }

    @Override
    public void handle(YopCallback callback) {
        LOGGER.debug("mock notify handle:{}", callback);
        // TODO 这里是业务处理
    }
}
