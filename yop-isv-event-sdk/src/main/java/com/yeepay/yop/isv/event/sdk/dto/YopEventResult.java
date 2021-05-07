package com.yeepay.yop.isv.event.sdk.dto;

import com.yeepay.yop.isv.event.sdk.enums.YopEventHandleStatus;

import java.io.Serializable;

/**
 * title: 通知结果<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-06-13 22:55
 */
public class YopEventResult implements Serializable {

    private static final long serialVersionUID = -1L;

    private static final YopEventResult SUCCESS_RESULT = new YopEventResult(YopEventHandleStatus.Success);

    /**
     * 通知状态
     */
    private YopEventHandleStatus status;

    /**
     * 错误消息
     */
    private String message;

    public YopEventResult(YopEventHandleStatus status) {
        this.status = status;
    }

    public YopEventResult(YopEventHandleStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static YopEventResult success() {
        return SUCCESS_RESULT;
    }

    public static YopEventResult fail(String message) {
        return new YopEventResult(YopEventHandleStatus.Fail, message);
    }

    public YopEventHandleStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        switch (status) {
            case Success:
                return status.name();
            default:
                return status.name() + ", cause:" + message;
        }
    }
}
