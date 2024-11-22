package com.zky.application.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private AuthorLogger authorLogger;
    
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

    @Before("execution(* com.zky.application.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String author = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            author = request.getHeader("author");
        }
        authorLogger.logAuthor(author);
    }
} 
