package coHelp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    @After("execution(* coHelp.service.SignUpService.activate(..))")
    public void afterActivate(JoinPoint jp) {
        log.debug("new user activated");
    }
    


    @After("execution(* coHelp.service.SignUpService.activate(..))")
    public void afterSignUp(JoinPoint jp) {
        log.debug("new user signed up");
    }

    @Before("execution(* coHelp.service.TaskService.getTaskForDistance(..))")
    public void beforeGetTasks (JoinPoint joinPoint){
        log.debug("user get tasks for distance");
    }

    @Before("execution(* coHelp.contoller.ProfileController.getPage(..))")
    public void beforeGetProfile (JoinPoint joinPoint){
        log.debug("user loaded profle");
    }
}
