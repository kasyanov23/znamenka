package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.click.core.converter.LocalDateTimeConverter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;

/**
 * DTO сообщения от клиента
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Entity
@Table(name = "messages_from_client", schema = "common")
@Getter @Setter @Accessors(chain = true)
public class MessageFromClient implements BaseModel<Long> {

    /**
     * Синтетический первичный ключ
     */
    @Id
    @Column(name = "message_id")
    private Long id;

    /**
     * Код студии
     */
    @Column(name = "studio_id")
    private Integer studio;

    /**
     * Имя пользователя, которому надо доставить сообщение
     */
    @Column(name = "dist_username")
    private String destinationUsername;

    /**
     * Ссылка на клиента, который отправил сообщение
     */
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Текст сообщения
     */
    @Column(name = "message_text")
    private String text;

    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @PrePersist
    private void initCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
