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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;
import ru.click.cabinet.config.oauth2.util.OAuth2Provider;

import javax.servlet.Filter;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
@Order(6)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ClientContext oauth2ClientContext;
    @Autowired
    private ListableBeanFactory factory;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/webjars/**", "/", "/login/**").permitAll()
                .antMatchers("/sign-up/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
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
