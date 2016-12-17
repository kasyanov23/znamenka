package ru.click.cabinet.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import ru.click.cabinet.config.formatter.DateFormatter;
import ru.click.cabinet.config.formatter.LocalDateTimeConverter;
import ru.click.cabinet.config.formatter.LocalDateTimeFormatter;
import ru.click.cabinet.config.formatter.TimestampFormatter;

import java.util.List;

/**
 * <p>
 * Web конфигурация
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Configuration
public class WebConfig extends SpringDataWebConfiguration {

    /**
     * Расположение ресурсов
     */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};



    /**
     * {@inheritDoc}
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        HandlerMethodArgumentResolver pageableResolverOne = pageableResolver();
        argumentResolvers.add(pageableResolverOne);
    }


    /**
     * {@inheritDoc}
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Bean
    @Override
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver(sortResolver());
        pageableResolver.setOneIndexedParameters(true);

        return pageableResolver;
    }

    /**
     * Включает использование локали через заголовок запроса
     *
     * @return localeResolver
     * @see AcceptHeaderLocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }



    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatter(new TimestampFormatter());
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new LocalDateTimeFormatter());

        registry.addConverter(new LocalDateTimeConverter());
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

}
