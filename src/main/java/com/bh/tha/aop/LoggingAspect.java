package com.bh.tha.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public static Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controller() {
        //Pointcut for method executon within Controllers
    }

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {

    }

    /**
     * This aspect will log execution time for calls on all public methods within classes
     * annoted with @RestController
     * On a larger project, the logs created by this aspect could then be fed into an ELK stack to provide
     * metrics on API methods performance
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controller() && anyPublicOperation()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        LOGGER.info("Method " + className + "." + methodName + " ()" + " execution time : "
                + elapsedTime + " ms");
        return result;
    }
}
