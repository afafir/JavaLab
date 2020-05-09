package coHelp.aspect;


import coHelp.model.Mail;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Aspect
@Component
public class EmailAspect {
    @Autowired
    JavaMailSender sender;


    @AfterReturning(value = "execution(* coHelp.service.SignUpService.createMail(..))", returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        System.out.println(1);
        Mail toSend = (Mail) retVal;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(toSend.getMailTo());
            helper.setText(toSend.getMailContent(), true);
            helper.setSubject(toSend.getMailSubject());
            helper.setFrom(toSend.getMailFrom());
            sender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException();
        }
    }


}
