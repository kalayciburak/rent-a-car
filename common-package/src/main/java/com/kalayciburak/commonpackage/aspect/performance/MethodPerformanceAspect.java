package com.kalayciburak.commonpackage.aspect.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

@Aspect
@Component
public class MethodPerformanceAspect {
    private static final Logger log = LoggerFactory.getLogger(MethodPerformanceAspect.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = currentTimeMillis();
        var result = joinPoint.proceed();
        var duration = currentTimeMillis() - startTime;
        var message = format("Method %s took %dms", joinPoint.getSignature().getName(), duration);
        log.warn(message);

        return result;
    }
}
