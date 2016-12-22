package ru.click.cabinet.repository;

import ru.click.cabinet.repository.model.ClientTraining;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для управлениями тренировками.
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface TrainingManager {

    /**
     * Возвращает последние 60 проведенных тренировок клиента
     *
     * @param clientId идентификатор клиента
     * @return список тренировок клиента
     */
    List<ClientTraining> getLast60Trainings(Long clientId);

    /**
     * Возвращает баланс тренировок. Если баланс пуст,
     * то клиент не купил ни одного абонемента
     *
     * @param clientId идентификатор клиента
     * @return баланс тренировок
     */
    Optional<Integer> getBalanceOfTraining(Long clientId);
}
