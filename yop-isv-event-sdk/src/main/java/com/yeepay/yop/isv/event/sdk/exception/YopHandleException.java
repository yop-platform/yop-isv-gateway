package com.yeepay.yop.isv.event.sdk.exception;

/**
 * title: 商户处理异常<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-06-13 19:19
 */
public class YopHandleException extends YopBizException {

    private static final String CODE = "yop.handle.error";

    public YopHandleException() {
    }

    public YopHandleException(String message) {
        super(message);
    }

    public YopHandleException(String message, Object... args) {
        super(message, args);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
