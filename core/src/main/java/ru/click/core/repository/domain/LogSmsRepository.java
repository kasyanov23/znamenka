package ru.click.core.repository.domain;

import org.springframework.data.repository.CrudRepository;
import ru.click.core.model.LogSms;

/**
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface LogSmsRepository extends CrudRepository<LogSms, Long> {
}
