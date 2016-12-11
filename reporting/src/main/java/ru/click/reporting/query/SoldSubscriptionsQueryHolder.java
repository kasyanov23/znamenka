package ru.click.reporting.query;

import ru.click.reporting.model.SoldSubscriptions;

import static ru.click.reporting.util.IOUtils.fileToString;

public class SoldSubscriptionsQueryHolder implements QueryHolder<SoldSubscriptions> {

    private final String query = fileToString("sql/sold_subscriptions.sql");

    @Override
    public String getQuery() {
        return query;
    }
}
