/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.sdk.resolver.param;

import com.yeepay.yop.isv.event.sdk.dto.DecryptParamDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * title: 老商户通知请求参数解析器 接口<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2021/4/21 17:44
 */
public interface NotifyRequestParamResolver {

    DecryptParamDTO resolve(HttpServletRequest req);

}
