package emailSender.util.exception.MailUtil;

import emailSender.model.Mail;
import emailSender.model.Token;
import emailSender.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component("emailFreemarkerUtil")
public class PreparerFreemarkerMail implements PreparerMail {

    @Autowired
    JavaMailSender sender;
    @Autowired
    Configuration fmConfigFactoryBean;
    public Mail createActivateMail(User user) {
        Token token = Token.builder().user(user)
                .token(UUID.randomUUID().toString())
                .build();
        Map<String, Object> model = new HashMap();
        model.put("name", user.getName());
        model.put("token", token.getToken());
        Template t = null;
        String html = null;
        try {
            t = fmConfigFactoryBean.getTemplate("email-template.ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return Mail.builder().mailFrom("javalabqwerty@gmail.com")
                .mailSubject("Registration")
                .mailTo(user.getEmail())
                .model(model)
                .mailContent(html)
                .build();
    }

}
