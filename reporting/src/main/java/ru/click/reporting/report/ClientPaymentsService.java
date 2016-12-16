package ru.click.reporting.report;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.click.reporting.model.ClientPayments;
import ru.click.reporting.query.QueryHolder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

@Service
public class ClientPaymentsService {

    private final NamedParameterJdbcOperations operations;

    private final QueryHolder clientPaymentsQueryHolder;

    private static final RowMapper<ClientPayments> rowMapper = (rs, i) -> ClientPayments
            .builder()
            .clientName(rs.getString("client_name"))
            .paymentDate(rs.getDate("payment_date"))
            .productName(rs.getString("product_name"))
            .sumPayments(rs.getLong("sum_payments"))
            .leftToAdd(rs.getLong("left_to_add"))
            .trainerName(rs.getString("trainer_name"))
            .build();

    @Autowired
    public ClientPaymentsService(NamedParameterJdbcOperations operations, QueryHolder<ClientPayments> clientPaymentsQueryHolder) {
        notNull(operations, "Jdbc Operations must not be null");
        notNull(clientPaymentsQueryHolder, "Query holder must not be null");
        this.operations = operations;
        this.clientPaymentsQueryHolder = clientPaymentsQueryHolder;
    }

    public List<ClientPayments> clientPayments(LocalDate from, LocalDate to, Long clientId, Long trainerId) {
        val sql = clientPaymentsQueryHolder.getQuery();
        Map<String, Object> queryParams = new HashMap<>(4);
        queryParams.put("from", from);
        queryParams.put("to", to);
        queryParams.put("clientId", clientId);
        queryParams.put("trainerId", trainerId);
        return operations.query(sql, queryParams, rowMapper);
    }
}
