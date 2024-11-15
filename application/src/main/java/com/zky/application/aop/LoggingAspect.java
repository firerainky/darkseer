package com.zky.application.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    @Around("execution(* com.zky.application.*.*(..))") // This pointcut matches all methods in your application package
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        Object result = joinPoint.proceed(); // Execute the actual method
        
        long endTime = System.currentTimeMillis();
        System.out.printf("Method %s executed in %d ms%n", 
            joinPoint.getSignature().getName(), 
            (endTime - startTime));
        String javaVersion = System.getProperty("java.specification.version");
        System.out.println("Java version: " + javaVersion);
        return result;
    }
} 