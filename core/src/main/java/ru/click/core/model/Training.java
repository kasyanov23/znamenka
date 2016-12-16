package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;
import ru.click.core.converter.LocalDateTimeConverter;
import ru.click.core.converter.BooleanConverter;
import ru.click.core.listener.CreatedTrainingListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * <p>
 * <p>
 * Создан 01.08.2016
 * <p>
 * Изменения:
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Entity(name = "trainings")
@NamedEntityGraph(
        name = "Training.Graph",
        attributeNodes = {
                @NamedAttributeNode(value = "trainer"),
                @NamedAttributeNode(value = "client"),
                @NamedAttributeNode(value = "purchase"),
                @NamedAttributeNode(value = "status")
        }
)
@Getter @Setter
@EntityListeners(CreatedTrainingListener.class)
public class Training implements BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="trainings_seq")
    @SequenceGenerator(name = "trainings_seq", sequenceName = "trainings_training_id_seq", allocationSize = 1)
    @Column(name = "training_id")
    private Long id;

    @Column(name = "start")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime start;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "trainer_id", updatable = false, insertable = false)
    private Trainer trainer;

    @Column(name = "trainer_id")
    private Long trainerId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "client_id", updatable = false, insertable = false)
    private Client client;

    @Column(name = "client_id")
    private Long clientId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "purchase_id", updatable = false, insertable = false)
    private Purchase purchase;

    @Column(name = "purchase_id")
    private Long purchaseId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "status_id", updatable = false, insertable = false)
    private TrainingStatus status;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "comment")
    private String comment;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "pass_for_auto")
    private Boolean passForAuto;

    public Training setStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public Training setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
        return this;
    }

    public Training setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Training setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
        return this;
    }

    public Training setStatusId(Long statusId) {
        this.statusId = statusId;
        return this;
    }

    public Training setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Training setPassForAuto(Boolean passForAuto) {
        this.passForAuto = passForAuto;
        return this;
    }
}
