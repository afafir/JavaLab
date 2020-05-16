import emailSender.config.ApplicationContextConfig;
import emailSender.model.User;
import emailSender.repository.UserRepository;
import emailSender.repository.UserRepositoryJdbcTemplateImpl;
import emailSender.service.EmailService;
import emailSender.service.EmailServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;

public class Test {
    public static void main(String[] args) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        UserRepository repository = springContext.getBean(UserRepositoryJdbcTemplateImpl.class);
        EmailService emailService = springContext.getBean(EmailServiceImpl.class);
        User user = User.builder()
                .id((long) 5)
                .email("volnuhinegor@mail.ru")
                .password("123")
                .name("Egor")
                .build();
        emailService.sendMessage(user);
    }
}
