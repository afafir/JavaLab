package coHelp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "coHelp")
public class LocalizationConfig implements WebMvcConfigurer {
    @Bean
    public FreeMarkerConfigurer getConf() {
        final FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setTemplateLoaderPath("/WEB-INF/views");
        result.setDefaultEncoding("UTF-8");
        return result;
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = getConf().getConfiguration();
        configuration.setEncoding(new Locale("ru"), "utf-8");
        return configuration;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setContentType("text/html; charset=utf-8");
        resolver.setSuffix(".ftl");
        return resolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("localeInfo");
        cookieLocaleResolver.setCookieMaxAge(60 * 60 * 24 * 365);
        return cookieLocaleResolver;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/");
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }




    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(new Locale("ru"));
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public CharacterEncodingFilter filterRegistrationBean() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
        return codesResolver;
    }
}
