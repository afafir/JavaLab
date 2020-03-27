package downloader.aspect;


import downloader.models.FileInfo;
import downloader.models.Mail;
import downloader.models.user.Token;
import downloader.utils.MailPreparer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("activation")
    @Autowired
    MailPreparer mailPreparer;


    @AfterReturning(value = "execution(* downloader.services.SignUpService.signUp(..))", returning ="retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal)  {
        System.out.println(1);
        Token token = (Token) retVal;
        Mail toSend = mailPreparer.createMail(token);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
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
