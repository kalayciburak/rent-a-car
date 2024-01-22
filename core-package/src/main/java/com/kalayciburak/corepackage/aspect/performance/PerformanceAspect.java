package tr.com.prometaerp.core.aspect.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        double executionTimeInSeconds = (System.currentTimeMillis() - start) / 1000.0;
        log.warn(String.format("%s metodu %.3f saniye sürdü.", joinPoint.getSignature().getName(), executionTimeInSeconds));

        return proceed;
    }
}
