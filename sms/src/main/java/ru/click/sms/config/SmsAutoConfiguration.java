package ru.click.sms.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * Создан 15.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Configuration
@ComponentScan("ru.click.sms")
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(SmsPropertiesHolder.class)
public class SmsAutoConfiguration {

    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }

}
