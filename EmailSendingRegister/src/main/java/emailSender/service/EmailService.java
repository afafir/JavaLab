package emailSender.service;

import emailSender.dto.UserDto;
import emailSender.model.Mail;
import emailSender.model.Token;
import emailSender.model.User;

import java.util.Optional;


public interface EmailService {
    void sendMessage(Mail mail);

}
