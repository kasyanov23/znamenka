package ru.click.sms.config;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

/**
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@ConfigurationProperties(prefix = "spring.sms")
@Data
public class SmsPropertiesHolder {

    private String host;
    private String token;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private Boolean test = false;
    private String from;

    public Optional<String> getHost() {
        return Optional.ofNullable(host);
    }

    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }

    public Optional<String> getFrom() {
        return Optional.ofNullable(from);
    }
}
