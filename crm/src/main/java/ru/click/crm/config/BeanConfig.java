package ru.click.crm.config;


import com.google.api.services.calendar.Calendar;
import lombok.val;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.click.core.util.AutowireHelper;
import ru.click.crm.security.GoogleAuthorizer;
import ru.click.crm.util.locale.ExtResourceBundleSource;

import java.io.IOException;

/**
 * <p>Конфигуратор бинов
 * <p>
 * Создан 04.08.2016
 * <p>
 * Изменения:
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Configuration
@EnableAsync
public class BeanConfig {

    /**
     * Proxy для перехвата исключений
     * @return возвращает proxy
     */
    @Bean(name = "dataService")
    public ProxyFactoryBean facadeRepoProxy() {
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setInterceptorNames("repositoryThrowsAdvice");
        proxy.setTargetName("convertService");

        return proxy;
    }

    /**
     * Создает бин для доступа к пулу сообщений.
     * Используется для локализации
     * @return бин для доступа к локализованным сообщениям
     */
    @Bean
    public MessageSource messageSource() {
        val source = new ExtResourceBundleSource();
        source.setBasename("static\\locale\\messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }


    @Bean
    public Calendar calendar(@Autowired GoogleAuthorizer authorizer) throws IOException {
        return authorizer.getCalendar();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public AutowireHelper autowireHelper() {
        return AutowireHelper.getInstance();
    }

}
