package ru.click.reporting.query;

import ru.click.reporting.model.ConductedTraining;

import static ru.click.reporting.util.IOUtils.fileToString;

/**
 * <p>
 * Создан 14.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class ConductedTrainingsQueryHolder implements QueryHolder<ConductedTraining> {

    private final String query = fileToString("sql/conducted_trainings.sql");

    @Override
    public String getQuery() {
        return query;
    }
}
