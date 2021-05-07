/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.yop.isv.event.sdk.handler;

import com.yeepay.yop.isv.event.sdk.dto.YopEvent;
import com.yeepay.yop.isv.event.sdk.dto.YopEventResult;
import com.yeepay.yop.isv.event.sdk.exception.YopHandleException;

/**
 * title: YOP 事件处理器注解<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-05-14 16:32
 */
public interface YopEventHandler {

    /**
     * 事件类型
     */
    String getEventType();

    /**
     * 处理
     *
     * @param event 事件
     * @return
     * @throws YopHandleException
     */
    YopEventResult handle(YopEvent event) throws YopHandleException;

}
