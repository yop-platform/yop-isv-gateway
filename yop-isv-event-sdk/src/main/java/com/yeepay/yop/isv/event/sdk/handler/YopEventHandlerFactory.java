package com.yeepay.yop.isv.event.sdk.handler;

import com.yeepay.yop.isv.event.sdk.handler.impl.YopWildCardEventHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * title: YOP 事件处理器工厂<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-05-14 16:45
 */
public class YopEventHandlerFactory {

    private static final Map<String, YopEventHandler> YOP_EVENT_HANDLERS = new ConcurrentHashMap<>();

    static {
        register("default", new YopWildCardEventHandler());
    }

    public static YopEventHandler getHandler(String eventType) {
        assert StringUtils.isNotEmpty(eventType);
        YopEventHandler handler = YOP_EVENT_HANDLERS.get(eventType);
        if (null == handler) {
            return YOP_EVENT_HANDLERS.get("default");
        } else {
            return handler;
        }
    }

    public static void register(String eventType, YopEventHandler yopEventHandler) {
        assert StringUtils.isNotEmpty(eventType);
        assert null != yopEventHandler;
        YOP_EVENT_HANDLERS.put(eventType, yopEventHandler);
    }

}
