package com.kalayciburak.commonpackage.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class PerformanceInterceptor implements HandlerInterceptor {
    private static final long SLOW_REQUEST_THRESHOLD = 200;
    private static final Logger log = getLogger(PerformanceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", currentTimeMillis());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        var startTime = (long) request.getAttribute("startTime");
        var duration = currentTimeMillis() - startTime;
        var requestURI = request.getRequestURI();
        var message = format("Slow request detected: %s took %dms", requestURI, duration);
        if (duration > SLOW_REQUEST_THRESHOLD) log.warn(message);
    }
}
