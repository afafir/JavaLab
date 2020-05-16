package chat.config;

import chat.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "chat")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Autowired
    Environment environment;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(driverManagerDataSource());
        entityManagerFactory.setPackagesToScan("chat");
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(properties());
        return entityManagerFactory;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");

        return properties;
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
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
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

    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setContentType("text/html");
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        return viewResolver;
    }


    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreeMarkerConfiguration() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();
        config.setDefaultEncoding("UTF-8");
        config.setTemplateLoaderPath("/WEB-INF/views/");
        return config;
    }


}
