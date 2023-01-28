package com.muv.phonebook.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is aspect that processes methods of repository classes
 * @author muv11
 * @version 1.0
 * @see com.muv.phonebook.repository */
@Component
@Aspect
public class RepositoryLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.muv.phonebook.repository.*.create*(..))")
    public void createPointcut() {}
    @Pointcut("execution(* com.muv.phonebook.repository.*.update*(..))")
    public void updatePointcut() {}
    @Pointcut("execution(* com.muv.phonebook.repository.*.delete*(..))")
    public void deletePointcut() {}
    @Pointcut("execution(* com.muv.phonebook.repository.*.find*(..))")
    public void findPointcut() {}

    @After("createPointcut() && args(obj)")
    public void createMethods(Object obj) {
        logger.info("THE ROW WITH THE FOLLOWING DATA " + obj.toString() + " WAS CREATED");
    }

    @After("updatePointcut() && args(.., id)")
    public void updateMethods(Long id) {
        logger.info("THE ROW WITH ID = " + id + " WAS UPDATED");
    }

    @After("deletePointcut() && args(id)")
    public void deleteMethods(Long id) {
        logger.info("THE ROW WITH ID = " + id + " WAS DELETED");
    }

    @Before("findPointcut()")
    public void enterFindMethods() {
        logger.info("ENTERING find METHOD");
    }

    @After("findPointcut()")
    public void leaveFindMethods() {
        logger.info("LEAVING find METHOD");
    }

}
