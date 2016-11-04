package ru.znamenka.persons.config.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.znamenka.persons.config.oauth2.util.OAuth2Provider;

@Configuration
public class FacebookConfig {

    @Bean
    @ConfigurationProperties("facebook")
    public OAuth2Provider facebook() {
        return new OAuth2Provider();
    }
}
