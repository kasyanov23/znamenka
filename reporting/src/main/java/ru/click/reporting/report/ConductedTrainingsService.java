package ru.click.reporting.report;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.click.reporting.model.ConductedTraining;
import ru.click.reporting.query.QueryHolder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

/**
 * <p>
 * Создан 14.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class ConductedTrainingsService {

    private final NamedParameterJdbcOperations operations;

    private final QueryHolder conductedTrainingsQueryHolder;

    private static final RowMapper<ConductedTraining> rowMapper = (rs, i) -> ConductedTraining
            .builder()
            .clientName(rs.getString("client_name"))
            .date(rs.getDate("date").toLocalDate())
            .time(rs.getTime("time").toLocalTime())
            .status(rs.getString("status"))
            .build();

    @Autowired
    public ConductedTrainingsService(
            NamedParameterJdbcOperations operations,
            QueryHolder<ConductedTraining> conductedTrainingsQueryHolder
    ) {
        notNull(operations, "Jdbc Operations must not be null");
        notNull(conductedTrainingsQueryHolder, "Query holder must not be null");
        this.operations = operations;
        this.conductedTrainingsQueryHolder = conductedTrainingsQueryHolder;
    }

    public List<ConductedTraining> conductedTrainings(LocalDate from, LocalDate to, Long trainerId) {
        val sql = conductedTrainingsQueryHolder.getQuery();
        Map<String, Object> queryParams = new HashMap<>(4);
        queryParams.put("from", from);
        queryParams.put("to", to);
        queryParams.put("trainerId", trainerId);
        return operations.query(sql, queryParams, rowMapper);
    }
}
