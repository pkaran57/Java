package com.ker.springboot.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
/**
 * @see com.ker.springboot.zip.ZipCodePointAccessAOP
 */
public class TimeTracking {

    @Around("@annotation(com.ker.springboot.aop.TrackTime)")
    public Object logTime(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long callTime = System.currentTimeMillis();
        final Object result = proceedingJoinPoint.proceed();
        final long callCompletionTime = System.currentTimeMillis();
        log.info("Time taken for execution of {} method annotated with @TrackTime = {} ms", proceedingJoinPoint.getSignature().getName(), callCompletionTime - callTime);
        return result;
    }
}
