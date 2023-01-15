package com.muv.phonebook.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.muv.phonebook.controller.*.get*())")
    public void controllerGetPointcut() {}
    @Pointcut("execution(* com.muv.phonebook.controller.*.post*())")
    public void controllerPostPointcut() {}

    @Before("controllerGetPointcut()")
    public void enterGetMappingMethods() {
        logger.info("ENTERING GetMapping METHOD");
    }

    @Before("controllerPostPointcut()")
    public void enterPostMappingMethods() {
        logger.info("ENTERING PostMapping METHOD");
    }

}
