package ru.znamenka.service.subsystem.training;

import ru.znamenka.jpa.model.CalendarEvent;
import ru.znamenka.represent.domain.TrainingApi;

import java.sql.Date;
import java.util.List;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface IEventService {

    List<CalendarEvent> loadEventsBusy(Date startDate, Date endDate);

    List<CalendarEvent> loadEvents(Date startDate, Date endDate);

    List<CalendarEvent> loadDutyEvents(Date startDate, Date endDate);

    @Deprecated
    void postToCalendar(TrainingApi training);
}
