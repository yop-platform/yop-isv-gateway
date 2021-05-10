package com.yeepay.yop.isv.event.gateway.servlet;

import com.yeepay.yop.isv.event.sdk.dto.YopEvent;
import com.yeepay.yop.isv.event.sdk.dto.YopEventResult;
import com.yeepay.yop.isv.event.sdk.exception.YopHandleException;
import com.yeepay.yop.isv.event.sdk.handler.YopEventHandlerFactory;
import com.yeepay.yop.isv.event.sdk.resolver.RequestResolver;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * title: 通知接收servlet<br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-05-05 10:19
 */
@WebServlet(urlPatterns = "/yop-event/*")
public class YopEventProcessServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(YopEventProcessServlet.class);

    @Autowired
    @Qualifier("eventRequestResolver")
    private RequestResolver eventRequestResolver;

    @Autowired
    @Qualifier("notifyRequestResolver")
    private RequestResolver notifyRequestResolver;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String respStr = "";
        try {
            String eventType = req.getHeader("x-yop-event-type");
            YopEvent event;
            if (StringUtils.isNotEmpty(eventType)) {
                throw new YopHandleException("当前版本不支持事件，请升级");
                // TODO event = eventRequestResolver.resolve(req);
            } else {
                event = notifyRequestResolver.resolve(req);
            }

            final YopEventResult notifyResult = YopEventHandlerFactory.getHandler(event.getEventType()).handle(event);
            if (null != notifyResult) {
                respStr = notifyResult.toString();
            }
            resp.getOutputStream().write(respStr.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOGGER.error("error when handle yop event", e);
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

}
