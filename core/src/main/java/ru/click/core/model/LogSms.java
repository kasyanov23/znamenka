package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Entity
@Table(schema = "common", name = "sms_logs")
@Getter @Setter @Accessors(chain = true)
public class LogSms {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String phone;
    private String text;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogSms logSms = (LogSms) o;
        return Objects.equals(getPhone(), logSms.getPhone()) &&
                Objects.equals(getText(), logSms.getText()) &&
                Objects.equals(getStatus(), logSms.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhone(), getText(), getStatus());
    }
}
