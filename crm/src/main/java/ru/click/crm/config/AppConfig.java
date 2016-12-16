package ru.click.crm.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Основной класс конфигурации приложения
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Configuration
@Import({
        BeanBucketConfig.class
        , WebConfig.class
        , BeanConfig.class
        , SecurityConfig.class
})
@EnableAutoConfiguration
public class AppConfig {
}
