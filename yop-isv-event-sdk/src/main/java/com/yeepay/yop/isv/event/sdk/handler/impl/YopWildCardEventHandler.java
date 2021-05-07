package com.yeepay.yop.isv.event.sdk.handler.impl;

import com.yeepay.yop.isv.event.sdk.dto.YopEvent;
import com.yeepay.yop.isv.event.sdk.dto.YopEventResult;
import com.yeepay.yop.isv.event.sdk.exception.YopHandleException;
import com.yeepay.yop.isv.event.sdk.handler.YopBaseEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * title: 简单打印<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-05-14 16:38
 */
public class YopWildCardEventHandler extends YopBaseEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(YopWildCardEventHandler.class);

    @Override
    public String getEventType() {
        return "default";
    }

    @Override
    public YopEventResult handle(YopEvent event) throws YopHandleException {
        LOGGER.warn("you need to handle the new yop event, received: {}.", event);
        return YopEventResult.fail("商户未处理");
    }

}
