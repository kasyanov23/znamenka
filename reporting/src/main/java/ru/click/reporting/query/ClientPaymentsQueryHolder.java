package ru.click.reporting.query;

import org.springframework.stereotype.Service;
import ru.click.reporting.model.ClientPayments;

import static ru.click.reporting.util.IOUtils.fileToString;

@Service
public class ClientPaymentsQueryHolder implements QueryHolder<ClientPayments> {

    private final String query = fileToString("/sql/client_payments.sql");

    @Override
    public String getQuery() {
        return query;
    }

}
