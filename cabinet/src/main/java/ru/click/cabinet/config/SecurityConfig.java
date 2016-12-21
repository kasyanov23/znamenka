package ru.click.cabinet.config;

import lombok.val;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;
import ru.click.cabinet.config.oauth2.util.OAuth2Provider;
import ru.click.core.repository.UserDao;
import ru.click.core.repository.domain.LkUserRepository;

import javax.servlet.Filter;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
@Order(6)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2ClientContext oauth2ClientContext;
    private final ListableBeanFactory factory;
    private final LkUserRepository userRepository;

    @Autowired
    public SecurityConfig(
            OAuth2ClientContext oauth2ClientContext,
            ListableBeanFactory factory,
            LkUserRepository userRepository
    ) {
        this.oauth2ClientContext = oauth2ClientContext;
        this.factory = factory;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider());
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDao());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDao() {
        return new UserDao<>(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**").permitAll()
                .antMatchers("/sign-up/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/training", true)
                .and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        ;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }


    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(ssoFilters());
        return filter;
    }


    private List<Filter> ssoFilters() {
        val oauth2Providers = factory.getBeansOfType(OAuth2Provider.class).values();
        return oauth2Providers
                .stream()
                .map(p -> getFilter(p.getClient(), p.getResource(), p.getLogin()))
                .collect(Collectors.toList());
    }

    private OAuth2ClientAuthenticationProcessingFilter getFilter(OAuth2ProtectedResourceDetails details, ResourceServerProperties props, String loginUrl) {
        val filter = new OAuth2ClientAuthenticationProcessingFilter(loginUrl);
        val googleTemplate = new OAuth2RestTemplate(details, oauth2ClientContext);
        filter.setRestTemplate(googleTemplate);
        val tokenServices = new UserInfoTokenServices(props.getUserInfoUri(), details.getClientId());
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    @ConfigurationProperties("google")
    public OAuth2Provider google() {
        return new OAuth2Provider();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public OAuth2Provider facebook() {
        return new OAuth2Provider();
    }

}
