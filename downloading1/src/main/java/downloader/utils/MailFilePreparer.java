package downloader.utils;

import downloader.models.FileInfo;
import downloader.models.Mail;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component("downloading")
public class MailFilePreparer implements MailPreparer<FileInfo> {

    @Autowired
    FreeMarkerConfigurer freemarkerConfig;

    @Override
    public Mail createMail(FileInfo info) {
        Map<String, Object> model = new HashMap();
        model.put("link", info.getName() + "." + info.getExtension());
        String html = null;
        try {
            Template t = freemarkerConfig.getConfiguration().getTemplate("download-template.ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return Mail.builder().mailFrom("tynbcx@gmail.com")
                .mailSubject("File link")
                .mailContent(html)
                .mailTo(info.getUploadedUser().getEmail())
                .model(model)
                .build();
    }
}
