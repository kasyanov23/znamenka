package ru.click.cabinet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.click.cabinet.repository.model.ClientTraining;
import ru.click.cabinet.repository.query.QueriesLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonMap;

/**
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Repository
public class TrainingManagerImpl implements TrainingManager {

    private final NamedParameterJdbcOperations operations;

    @Autowired
    public TrainingManagerImpl(NamedParameterJdbcOperations operations) {
        this.operations = operations;
    }


    @Override
    public List<ClientTraining> getLast60Trainings(Long clientId) {
        return operations
                .getJdbcOperations()
                .query(QueriesLoader.clientTrainings, Mappers.clientTraining, clientId);
    }

    @Override
    public Optional<Integer> getBalanceOfTraining(Long clientId) {
        return operations.queryForObject(
                QueriesLoader.balanceOfTraining,
                singletonMap("clientId", clientId),
                Mappers.balanceOfTraining
        );
    }


    private Timestamp toTimestamp(Date startDate) {
        LocalDateTime t = LocalDateTime.of(startDate.toLocalDate(), LocalTime.MAX);
        return Timestamp.valueOf(t);
    }
}
