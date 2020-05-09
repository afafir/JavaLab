package coHelp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @After("execution(* coHelp.service.SignUpService.activate(..))")
    public void afterActivate(JoinPoint jp) {
        System.out.println("new user activated");;
    }


    @After("execution(* coHelp.service.SignUpService.activate(..))")
    public void afterSignUp(JoinPoint jp) {
        System.out.println("new user tried to signUp");
    }
}
