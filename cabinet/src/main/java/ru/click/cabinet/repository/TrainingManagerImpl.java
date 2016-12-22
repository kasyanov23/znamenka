package ru.click.cabinet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.click.cabinet.repository.model.ClientTraining;
import ru.click.cabinet.repository.query.QueriesHolder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonMap;

/**
 * Реализация менеджера тренировок
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Repository
public class TrainingManagerImpl implements TrainingManager {

    /**
     * Исполнитель запросов, позволяющий использовать именнованные параметры
     */
    private final NamedParameterJdbcOperations operations;

    /**
     * Конструктор для внедрения зависимостей
     * @param operations исполнитель запросов
     */
    @Autowired
    public TrainingManagerImpl(NamedParameterJdbcOperations operations) {
        this.operations = operations;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientTraining> getLast60Trainings(Long clientId) {
        return operations
                .getJdbcOperations()
                .query(QueriesHolder.clientTrainings, Mappers.clientTraining, clientId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getBalanceOfTraining(Long clientId) {
        return operations.queryForObject(
                QueriesHolder.balanceOfTraining,
                singletonMap("clientId", clientId),
                Mappers.balanceOfTraining
        );
    }


    private Timestamp toTimestamp(Date startDate) {
        LocalDateTime t = LocalDateTime.of(startDate.toLocalDate(), LocalTime.MAX);
        return Timestamp.valueOf(t);
    }
}
