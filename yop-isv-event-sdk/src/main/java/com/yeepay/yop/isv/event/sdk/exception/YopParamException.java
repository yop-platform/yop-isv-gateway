package com.yeepay.yop.isv.event.sdk.exception;

/**
 * title: 参数异常<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-06-13 17:19
 */
public class YopParamException extends YopBizException {

    private static final String CODE = "yop.param.error";

    public YopParamException() {
    }

    public YopParamException(String message) {
        super(message);
    }

    public YopParamException(String message, Object... args) {
        super(message, args);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
