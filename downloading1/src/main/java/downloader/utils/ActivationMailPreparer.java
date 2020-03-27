package downloader.utils;

import downloader.models.Mail;
import downloader.models.user.Token;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("activation")
public class ActivationMailPreparer implements MailPreparer<Token> {

    @Autowired
    FreeMarkerConfigurer freemarkerConfig;

    @Override
    public Mail createMail(Token token) {
        Map<String, Object> model = new HashMap();
        model.put("link", token.getToken());
        String html = null;
        try {
            Template t = freemarkerConfig.getConfiguration().getTemplate("email-template.ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return Mail.builder().mailFrom("javalabqwerty@gmail.com")
                .mailSubject("Activate your account")
                .mailContent(html)
                .model(model)
                .mailTo(token.getUser().getEmail())
                .mailContent(html)
                .build();
    }
}

