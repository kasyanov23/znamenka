package ru.click.sms.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.dezhik.sms.sender.AsyncSenderService;
import ru.dezhik.sms.sender.SenderService;
import ru.dezhik.sms.sender.SenderServiceConfiguration;
import ru.dezhik.sms.sender.SenderServiceConfigurationBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.util.Assert.notNull;

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

    private final SmsPropertiesHolder smsPropertiesHolder;

    @Autowired
    public SmsAutoConfiguration(SmsPropertiesHolder smsPropertiesHolder) {
        notNull(smsPropertiesHolder, "Sms properties must not be null");
        this.smsPropertiesHolder = smsPropertiesHolder;
    }

    @Bean
    public SenderServiceConfiguration senderServiceConfiguration() {
        val builder = SenderServiceConfigurationBuilder.create();
        smsPropertiesHolder.getFrom().ifPresent(builder::setFromName);
        smsPropertiesHolder.getToken().ifPresent(builder::setApiId);
        smsPropertiesHolder.getHost().ifPresent(builder::setApiHost);
        return builder
                .setLogin(smsPropertiesHolder.getLogin())
                .setPassword(smsPropertiesHolder.getPassword())
                .setTestSendingEnabled(smsPropertiesHolder.getTest())
                .setReturnPlainResponse(true)
                .setExecutorService(executorService())
                .build();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(3);
    }

    @Bean(destroyMethod = "shutdown")
    public AsyncSenderService asyncSenderService() {
        return new AsyncSenderService(senderServiceConfiguration());
    }

    @Bean
    public SenderService senderService() {
        return new SenderService(senderServiceConfiguration());
    }

}
