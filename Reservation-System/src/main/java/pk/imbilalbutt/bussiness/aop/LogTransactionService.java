package pk.imbilalbutt.bussiness.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogTransactionService {

    private static final Logger LOG = LoggerFactory.getLogger(LogTransactionService.class);

    @Pointcut("within(pk.imbilalbutt.bussiness.service..*)")
    public void logServiceMethod(){ }

    @Before("logServiceMethod()")
    public void beginTransaction() {
        LOG.info("LogTransactionService - beginTransaction() called.");
    }

    @Before("logServiceMethod()")
    public void logServiceMethods(JoinPoint jp) {

        String methodName = jp.getSignature().getName();

        LOG.info("LogTransactionService - logServiceMethods() - Executing method name : " + methodName);
    }

    @After("logServiceMethod()")
    public void afterTransaction(){
        LOG.info("LogTransactionService - afterTransaction() called.");
    }

    @AfterReturning("logServiceMethod()")
    public void commitTransaction(){
        LOG.info("LogTransactionService - commitTransaction() - Commit Successful.");
    }

    @AfterThrowing("logServiceMethod()")
    public void rollbackTransaction() {
        LOG.info("LogTransactionService - rollbackTransaction() - Rollback Successful : Couldn't perform commit.");
    }

}
