package ru.click.cabinet.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.click.cabinet.repository.TrainingManager;
import ru.click.core.model.CalendarEvent;
import ru.click.core.model.Client;
import ru.click.core.model.Training;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class TrainingService {

    @Autowired
    private TrainingManager trainingManager;


    public List<CalendarEvent> loadEvents(Date startDate, Date endDate, Long clientId) {
        List<Training> trainings = trainingManager.trainingsByPeriod(startDate, endDate, clientId);
        List<CalendarEvent> calendarEvents = new ArrayList<>(trainings.size());
        for (Training training : trainings) {
            val start = training.getStart();
            val end = start.plus(30L, MINUTES);
            Client client = training.getClient();
            val calendarEvent = CalendarEvent
                    .createEvent()
                    .id(training.getId())
                    .title(client.getFullName())
                    .start(start)
                    .end(end);

            calendarEvents.add(calendarEvent);
        }
        return calendarEvents;
    }
}
