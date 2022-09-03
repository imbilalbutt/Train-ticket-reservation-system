package pk.imbilalbutt.bussiness.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogTransactionAuthentication {

    private static final Logger LOG = LoggerFactory.getLogger(LogTransactionRepository.class);

    @Pointcut("this(pk.imbilalbutt.bussiness.controller.RESTController.AuthenticationRestController)")
    public void loggableAuthentications() {
        LOG.info("LogTransactionAuthentication - loggableAuthentications(). ");
    }

    @Before("loggableAuthentications()")
    public void logControllerMethod(JoinPoint jp) {

        String methodName = jp.getSignature().getName();

        LOG.info("LogTransactionAuthentication - logControllerMethod() - Executing method name : " + methodName);
    }

    @After("loggableAuthentications()")
    public void afterTransaction(){
        LOG.info("LogTransactionAuthentication - afterTransaction() called.");
    }
}
