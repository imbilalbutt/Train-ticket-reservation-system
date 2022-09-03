package pk.imbilalbutt.bussiness.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogTransactionRepository {

    private static final Logger LOG = LoggerFactory.getLogger(LogTransactionRepository.class);

    @Pointcut("within(pk.imbilalbutt.bussiness.repository..*)")
    public void loggableRepositoryMethods() {}

    @Before("loggableRepositoryMethods()")
    public void logRepositoryMethods(JoinPoint jp) {

        String methodName = jp.getSignature().getName();

        LOG.info("LogTransactionRepository - logRepositoryMethods() - Executing method name : " + methodName);
    }

    @After("loggableRepositoryMethods()")
    public void afterTransaction(){
        LOG.info("LogTransactionRepository - afterTransaction() called.");
    }
}
