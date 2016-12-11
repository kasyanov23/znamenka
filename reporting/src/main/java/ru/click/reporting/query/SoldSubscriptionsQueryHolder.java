package ru.click.reporting.query;

import org.springframework.stereotype.Component;
import ru.click.reporting.model.SoldSubscriptions;

import static ru.click.reporting.util.IOUtils.fileToString;

@Component
public class SoldSubscriptionsQueryHolder implements QueryHolder<SoldSubscriptions> {

    private final String query = fileToString("sql/sold_subscriptions.sql");

    @Override
    public String getQuery() {
        return query;
    }
}
