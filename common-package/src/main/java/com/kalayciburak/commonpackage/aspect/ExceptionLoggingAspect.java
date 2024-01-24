package com.kalayciburak.commonpackage.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {
    private static final String logType = "error_log";
    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    @Before("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void beforeAdvice() {
        MDC.put("log_type", logType);
    }

    @AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.ExceptionHandler)", returning = "response")
    public void logAfterReturning(JoinPoint joinPoint, Object response) {
        String logMessage = createLogMessage(joinPoint);
        log.error(String.format("%s => %s\n", logMessage, response));
        MDC.clear();
    }

    @AfterThrowing(pointcut = "@annotation(org.springframework.web.bind.annotation.ExceptionHandler)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String logMessage = createLogMessage(joinPoint);
        log.error(String.format("Error in %s\n", logMessage), exception);
        MDC.clear();
    }

    private String createLogMessage(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        return String.format("%s.%s", className, methodName);
    }
}


