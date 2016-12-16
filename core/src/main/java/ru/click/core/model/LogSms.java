package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

}
