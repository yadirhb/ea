package edu.mum.cs544.bank.aspect;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {
    @Autowired
    private ILogger logger;

    @Before("execution(* edu.mum.cs544.bank.dao.*.*(..))")
    public void logBankDaoPackage(JoinPoint jp) {
        logger.log("Method " + jp.getSignature().getName() + " called!");
    }

    @Around("execution(* edu.mum.cs544.bank.service.*.*(..))")
    // Applies to any method withing any class with any arguments.
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        String methodName = call.getSignature().getName();
        StopWatch sw = new StopWatch();
        sw.start(methodName);
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        logger.log("Time to execute " + methodName + " = " + totaltime + " ms");
        return retVal;
    }

    @After("execution(* edu.mum.cs544.bank.jms.IJMSSender.sendJMSMessage(String)) && args(text)")
    public void logSendJMSMessage(String text) {
        logger.log(" Message sent using IJMSSender = " + text);
    }
}
