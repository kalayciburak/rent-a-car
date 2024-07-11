package com.kalayciburak.commonpackage.aspect.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Aspect
@Component
public class SlowQueryAspect {
    private static final long SLOW_QUERY_THRESHOLD = 200;
    private static final Logger log = LoggerFactory.getLogger(SlowQueryAspect.class);

    @Around("execution(* com.kalayciburak..repository..*(..))")
    public Object logSlowQueries(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.currentTimeMillis();
        var result = joinPoint.proceed();
        var duration = System.currentTimeMillis() - startTime;
        var message = format("Slow query detected: %s took %dms", joinPoint.getSignature(), duration);
        if (duration > SLOW_QUERY_THRESHOLD) log.warn(message);

        return result;
    }
}