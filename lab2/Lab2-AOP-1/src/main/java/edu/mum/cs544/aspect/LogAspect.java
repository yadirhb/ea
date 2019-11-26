package edu.mum.cs544.aspect;

import edu.mum.cs544.IEmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static Logger logger = LogManager.getLogger(LogAspect.class);

    @After("execution(* edu.mum.cs544.IEmailSender.sendEmail(String, String)) && args(email, message)")
    public void logSendMail(JoinPoint jp, String email, String message) {
        String outgoingServer = ((IEmailSender) jp.getTarget()).getOutgoingMailServer();
        logger.warn(new Date() + " method = " + jp.getSignature().getName() + " address=" + email + ", message=" + message + " outgoing mail server=" + outgoingServer);
    }

    @Around("execution(* edu.mum.cs544.*DAO.*(*))") // Applies to any method withing any DAO class with any arguments.
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        String methodName = call.getSignature().getName();
        StopWatch sw = new StopWatch();
        sw.start(methodName);
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        logger.warn("Time to execute " + methodName + " = " + totaltime + " ms");
        return retVal;
    }
}
