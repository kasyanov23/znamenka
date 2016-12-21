package ru.click.cabinet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.click.cabinet.repository.model.ClientTraining;
import ru.click.cabinet.repository.query.QueriesLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Repository
public class TrainingManagerImpl implements TrainingManager {

    private final JdbcOperations operations;

    @Autowired
    public TrainingManagerImpl(JdbcOperations operations) {
        this.operations = operations;
    }


    @Override
    public List<ClientTraining> last30Trainings(Long clientId) {
        return operations.query(QueriesLoader.clientTrainings, Mappers.clientTraining, clientId);
    }


    private Timestamp toTimestamp(Date startDate) {
        LocalDateTime t = LocalDateTime.of(startDate.toLocalDate(), LocalTime.MAX);
        return Timestamp.valueOf(t);
    }
}
