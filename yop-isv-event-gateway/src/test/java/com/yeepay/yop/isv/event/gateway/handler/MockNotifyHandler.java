/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.gateway.handler;

import com.yeepay.yop.isv.event.sdk.dto.YopEvent;
import com.yeepay.yop.isv.event.sdk.dto.YopEventResult;
import com.yeepay.yop.isv.event.sdk.exception.YopHandleException;
import com.yeepay.yop.isv.event.sdk.handler.YopBaseEventHandler;
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
public class MockNotifyHandler extends YopBaseEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockNotifyHandler.class);

    @Override
    public String getEventType() {
        return "/yop-event/opr/payment";
    }

    @Override
    public YopEventResult handle(YopEvent event) throws YopHandleException {
        LOGGER.debug("mock notify handle:{}", event);
        // TODO 这里是业务处理
        return YopEventResult.success();
    }

}
