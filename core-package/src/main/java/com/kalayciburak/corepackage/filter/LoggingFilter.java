package com.kalayciburak.corepackage.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final String logType = "api_log";
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private static Map<String, String> getRequestHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private static Map<String, String> getResponseHeadersInfo(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String name : headerNames) {
            String value = response.getHeader(name);
            map.put(name, value);
        }
        return map;
    }

    private String getJsonString(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new PmtRuntimeException(ExceptionCodes.UNSUPPORTED_ENCODING, e.getMessage());
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long executeTime = System.currentTimeMillis() - startTime;

        String requestBodyString = getJsonString(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());

        String responseBodyString = getJsonString(responseWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());

        String requestHeadersString = new ObjectMapper().writeValueAsString(getRequestHeadersInfo(request));
        String responseHeadersString = new ObjectMapper().writeValueAsString(getResponseHeadersInfo(response));

        MDC.put("log_type", logType);
        MDC.put("uri", request.getRequestURI());
        MDC.put("request_method", request.getMethod());
        MDC.put("remote_address", request.getRemoteAddr());
        MDC.put("remote_port", String.valueOf(request.getRemotePort()));
        MDC.put("server_address", request.getLocalAddr());
        MDC.put("server_port", String.valueOf(request.getLocalPort()));
        MDC.put("status_code", String.valueOf(response.getStatus()));
        MDC.put("execution_time_ms", String.valueOf(executeTime));
        MDC.put("servlet_path", request.getServletPath());
        MDC.put("remote_user", request.getRemoteUser());
        MDC.put("request_body", requestBodyString);
        MDC.put("response_body", responseBodyString);
        MDC.put("request_headers", requestHeadersString);
        MDC.put("response_headers", responseHeadersString);

        log.debug("log_type={}, status_code={}, request_method={}, uri={}",
                logType, response.getStatus(), request.getMethod(), request.getRequestURI());

        responseWrapper.copyBodyToResponse();
        MDC.clear();
    }
}
