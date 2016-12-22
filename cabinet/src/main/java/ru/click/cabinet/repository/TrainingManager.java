package ru.click.cabinet.repository;

import ru.click.cabinet.repository.model.ClientTraining;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface TrainingManager {

    List<ClientTraining> getLast60Trainings(Long clientId);

    Optional<Integer> getBalanceOfTraining(Long clientId);
}
