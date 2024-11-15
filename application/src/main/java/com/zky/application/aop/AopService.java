package com.zky.application.aop;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
public class AopService {

    @Before("execution(* com.zky.application.aop.AopService.test(..))")
    public void before() {
        System.out.println("before");
    }
}
