package emailSender.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "emailSender")
public class ApplicationContextConfig {

    @Autowired
    Environment environment;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("db.driver"));
        ds.setUrl(environment.getProperty("db.url"));
        ds.setUsername(environment.getProperty("db.user"));
        ds.setPassword(environment.getProperty("db.password"));
        return ds;
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.user"));
        config.setDriverClassName(environment.getProperty("db.driver"));
        config.setPassword(environment.getProperty("db.password"));
        return config;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
        fmConfigFactoryBean.setTemplateLoaderPath("/templates/");
        return fmConfigFactoryBean;
    }
}
