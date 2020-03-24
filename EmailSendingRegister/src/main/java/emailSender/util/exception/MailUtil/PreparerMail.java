package emailSender.util.exception.MailUtil;

import emailSender.model.Mail;
import emailSender.model.Token;
import emailSender.model.User;
import freemarker.template.TemplateException;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

public interface PreparerMail {
    Mail createActivateMail(User user);

}
