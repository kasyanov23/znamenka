package ru.click.cabinet.repository;

import ru.click.core.model.Training;

import java.sql.Date;
import java.util.List;

/**
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface TrainingManager {

    List<Training> trainingsByPeriod(Date startDate, Date endDate, Long clientId);
}
