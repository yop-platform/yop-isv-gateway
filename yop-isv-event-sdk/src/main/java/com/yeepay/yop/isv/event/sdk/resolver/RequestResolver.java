/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.sdk.resolver;

import com.yeepay.yop.isv.event.sdk.dto.YopEvent;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * title: 请求解析器<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/6 16:24
 */
public interface RequestResolver {

    YopEvent resolve(HttpServletRequest req) throws IOException;

}
