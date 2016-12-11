package ru.click.reporting.report;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.click.reporting.model.SoldSubscriptions;
import ru.click.reporting.query.QueryHolder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

@Service
public class SoldSubscriptionsService {

    private final NamedParameterJdbcOperations operations;

    private final QueryHolder soldSubscriptionsQueryHolder;

    private static final RowMapper<SoldSubscriptions> rowMapper = (rs,i) -> SoldSubscriptions
            .builder()
            .clientName(rs.getString("client_name"))
            .purchaseDate(rs.getDate("purchase_date"))
            .productName(rs.getString("product_name"))
            .price(rs.getInt("price"))
            .payd(rs.getInt("payd"))
            .build();

    @Autowired
    public SoldSubscriptionsService(NamedParameterJdbcOperations operations, QueryHolder<SoldSubscriptions> soldSubscriptionsQueryHolder) {
        notNull(operations, "Jdbc Operations must not be null");
        notNull(soldSubscriptionsQueryHolder, "Query holder must not be null");
        this.operations = operations;
        this.soldSubscriptionsQueryHolder = soldSubscriptionsQueryHolder;
    }

    public List<SoldSubscriptions> soldSubscriptions(LocalDate from, LocalDate to, Long productId) {
        val sql = soldSubscriptionsQueryHolder.getQuery();
        Map<String, Object> queryParams = new HashMap<>(3);
        queryParams.put("from", Date.valueOf(from));
        queryParams.put("to", Date.valueOf(to));
        queryParams.put("product_id", productId);
        return operations.query(sql, queryParams, rowMapper);
    }
}
