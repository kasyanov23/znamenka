package ru.click.cabinet.repository;

import ru.click.cabinet.repository.model.ClientTraining;

import java.util.List;

/**
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface TrainingManager {

    List<ClientTraining> last30Trainings(Long clientId);
}
