package emailSender.service;


import emailSender.dto.UserDto;
import emailSender.model.Mail;
import emailSender.model.Token;
import emailSender.model.User;
import emailSender.repository.TokenRepository;
import emailSender.repository.UserRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender sender;
    @Autowired
    Configuration fmConfigFactoryBean;

    @Override
    public void sendMessage(Mail mail) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(mail.getMailTo());
            helper.setText(mail.getMailContent(), true);
            helper.setSubject(mail.getMailSubject());
            helper.setFrom(mail.getMailFrom());
            sender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException();
        }
    }

}
