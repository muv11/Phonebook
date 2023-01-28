package com.muv.phonebook.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is aspect that processes methods of controller classes
 * @author muv11
 * @version 1.0
 * @see com.muv.phonebook.controller */
@Component
@Aspect
public class ControllerLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.muv.phonebook.controller.*.get*())")
    public void controllerGetPointcut() {}
    @Pointcut("execution(* com.muv.phonebook.controller.*.post*(..))")
    public void controllerPostPointcut() {}
    @Pointcut("execution(* com.muv.phonebook.controller.*.put*(..))")
    public void controllerPutPointcut() {}
    @Pointcut("execution(* com.muv.phonebook.controller.*.delete*(..))")
    public void controllerDeletePointcut() {}
    @Pointcut("execution(* com.muv.phonebook.controller.*.redirect*())")
    public void controllerRedirectPointcut() {}

    @Before("controllerGetPointcut()")
    public void enterGetMethods() {
        logger.info("ENTERING GetMapping METHOD");
    }

    @Before("controllerPostPointcut()")
    public void enterPostMethods() {
        logger.info("ENTERING PostMapping METHOD");
    }

    @Before("controllerPutPointcut()")
    public void enterPutMethods() {
        logger.info("ENTERING PutMapping METHOD");
    }

    @Before("controllerDeletePointcut()")
    public void enterDeleteMethods() {
        logger.info("ENTERING DeleteMapping METHOD");
    }

    @Before("controllerRedirectPointcut()")
    public void enterRedirectMethods() {
        logger.info("ENTERING Redirect METHOD");
    }

}
