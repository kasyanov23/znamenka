package ru.click.reporting.report;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.click.reporting.model.BalanceTraining;
import ru.click.reporting.query.QueryHolder;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class BalanceTrainingService {

    private final NamedParameterJdbcOperations operations;

    private final QueryHolder balanceTrainingQueryHolder;

    private static final RowMapper<BalanceTraining> rowMapper = (rs, i) -> BalanceTraining
            .builder()
            .clientName(rs.getString("client_name"))
            .productName(rs.getString("product_name"))
            .trainingCount(rs.getInt("training_count"))
            .build();

    @Autowired
    public BalanceTrainingService(NamedParameterJdbcOperations operations, QueryHolder<BalanceTraining> balanceTrainingQueryHolder) {
        notNull(operations, "Jdbc Operations must not be null");
        notNull(balanceTrainingQueryHolder, "Query holder must not be null");
        this.operations = operations;
        this.balanceTrainingQueryHolder = balanceTrainingQueryHolder;
    }

    public List<BalanceTraining> balanceTrainings() {
        val sql = balanceTrainingQueryHolder.getQuery();
        return operations.query(sql, rowMapper);
    }
}
