package coHelp.config.security;

//import coHelp.filter.CustomFilter;

import coHelp.config.security.details.UserDetailsServiceImpl;
import coHelp.filter.CustomFilter;
import coHelp.service.FacebookConnectionSignUp;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.sql.DataSource;

@EnableWebSecurity
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"coHelp"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAspectJAutoProxy
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier(value = "jwtAuthenticationFilter")
    private GenericFilterBean jwtAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/signIn");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests().antMatchers("/rest/profile").authenticated();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }*/


    @Value("26cadcbcde462ad1b097be2008aa5603")
    String appSecret;

    @Value("945068059277455")
    String appId;


    @Autowired
    @Qualifier(value = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CharacterEncodingFilter characterEncodingFilter;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login*","/signin/**","/signup/**").permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember").tokenRepository(persistentTokenRepository());
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signIn")
                .deleteCookies("JSESSIONID", "remember")
                .invalidateHttpSession(true);
        http.formLogin()
                .loginPage("/signIn")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signIn?error")
                .permitAll();
       http.addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class);
        http.addFilterBefore(characterEncodingFilter, ChannelProcessingFilter.class);
    }

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }



}
