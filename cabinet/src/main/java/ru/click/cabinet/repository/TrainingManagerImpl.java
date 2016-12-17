package ru.click.cabinet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.click.cabinet.repository.query.QueriesLoader;
import ru.click.core.model.Training;

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

    @Autowired
    private JdbcOperations operations;

    @Override
    public List<Training> trainingsByPeriod(Date startDate, Date endDate, Long clientId) {
        String sql = QueriesLoader.trainings;

        return operations.query(sql, Mappers.training, toTimestamp(startDate), toTimestamp(endDate), clientId);
    }

    private Timestamp toTimestamp(Date startDate) {
        LocalDateTime t = LocalDateTime.of(startDate.toLocalDate(), LocalTime.MAX);
        return Timestamp.valueOf(t);
    }
}
