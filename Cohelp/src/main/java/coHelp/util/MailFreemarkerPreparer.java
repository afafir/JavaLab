package coHelp.util;

import coHelp.model.Mail;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

@Component
public class MailFreemarkerPreparer implements MailPreparer {

    @Autowired
    FreeMarkerConfigurer freemarkerConfig;

    @Override
    public String getMailTemplate(Map model) {
        Template t = null;
        try {
            t = freemarkerConfig.getConfiguration().getTemplate("email-template.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;

    }
}
