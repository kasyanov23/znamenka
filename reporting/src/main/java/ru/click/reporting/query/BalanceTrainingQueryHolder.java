package ru.click.reporting.query;

import org.springframework.stereotype.Service;
import ru.click.reporting.model.BalanceTraining;

import static ru.click.reporting.util.IOUtils.fileToString;
@Service
public class BalanceTrainingQueryHolder implements QueryHolder<BalanceTraining>{

    private final String query = fileToString("/sql/balance_training.sql");

    @Override
    public String getQuery() {
        return query;
    }
}
