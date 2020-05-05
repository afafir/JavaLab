package ru.javalab.queue.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {


    @Autowired
    public JavaMailSender javaMailSender;

    @Override
    public void sendDoc(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @SneakyThrows
    @Override
    public void sendMeme(String to, String subject) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        FileSystemResource file = new FileSystemResource("D:\\meme.jpg");
        helper.addAttachment("Invoice", file);
        javaMailSender.send(message);
    }
}
