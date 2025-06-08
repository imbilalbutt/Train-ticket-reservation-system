package pk.imbilalbutt.bussiness.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTransactionRepository {

    private static final Logger LOG = LoggerFactory.getLogger(LogTransactionRepository.class);

    @Pointcut("execution(* pk.imbilalbutt.bussiness.repository..*(..))")
    public void loggableRepositoryMethods() {}

    @Before("loggableRepositoryMethods()")
    public void logRepositoryMethods(JoinPoint jp) {

        String methodName = jp.getSignature().getName();

        LOG.info("Executing method name : {}", methodName);
    }

    @After("loggableRepositoryMethods()")
    public void logAfterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        LOG.info("Method executed: {}", methodName);
    }

}
