package com.yeepay.yop.isv.event.sdk.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * title: 通知事件<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-06-13 16:05
 */
public class YopEvent implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 事件id（标识每一次事件）
     */
    private String eventId;

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 业务数据（json）
     */
    private String bizData;

    /**
     * 发送时间
     */
    private Date createTime;

    /**
     * 其他信息（通知中的非业务参数，或者放在header里的部分字段，会根据需要放在此容器）
     */
    private Map<String, String> metaInfo = new HashMap<>();

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAppId() {
        return appId;
    }

    public String getBizData() {
        return bizData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Map<String, String> getMetaInfo() {
        return metaInfo;
    }

    public static final class Builder {

        private YopEvent yopEvent;

        public YopEvent build() {
            return yopEvent;
        }

        public static Builder builder() {
            final Builder builder = new Builder();
            builder.yopEvent = new YopEvent();
            builder.yopEvent.metaInfo = new HashMap<>(16);
            return builder;
        }

        public Builder withEventId(String eventId) {
            yopEvent.eventId = eventId;
            return this;
        }

        public Builder withEventType(String eventType) {
            yopEvent.eventType = eventType;
            return this;
        }

        public Builder withAppId(String appId) {
            yopEvent.appId = appId;
            return this;
        }

        public Builder withBizData(String bizData) {
            yopEvent.bizData = bizData;
            return this;
        }

        public Builder withCreateTime(Date createTime) {
            yopEvent.createTime = createTime;
            return this;
        }

        public Builder withMetaInfo(String key, String value) {
            yopEvent.metaInfo.put(key, value);
            return this;
        }
    }

    @Override
    public String toString() {
        return "YopEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", appId='" + appId + '\'' +
                ", bizData='" + bizData + '\'' +
                ", createTime=" + createTime +
                ", metaInfo=" + metaInfo +
                '}';
    }
}
