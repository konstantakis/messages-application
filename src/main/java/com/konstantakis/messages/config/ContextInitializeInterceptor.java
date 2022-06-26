package com.konstantakis.messages.config;

import com.konstantakis.messages.constants.AppConstants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class ContextInitializeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        MDC.put(AppConstants.TRACE_ID_HEADER, UUID.randomUUID().toString());
        return true;
    }
}
