package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Long id;
    private String phone;
    private String text;
    private String status;

}
