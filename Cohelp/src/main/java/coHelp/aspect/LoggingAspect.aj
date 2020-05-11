package coHelp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @After("execution(* coHelp.service.SignUpService.activate(..))")
    public void afterActivate(JoinPoint jp) {
        log.info("new user activated");
    }


    @After("execution(* coHelp.service.SignUpService.activate(..))")
    public void afterSignUp(JoinPoint jp) {
        log.info("new user signed up");
    }
}
