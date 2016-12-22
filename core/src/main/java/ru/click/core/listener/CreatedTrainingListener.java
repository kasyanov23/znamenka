package ru.click.core.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import ru.click.core.model.CalendarEvent;
import ru.click.core.model.Training;
import ru.click.core.service.SmsWrapperService;
import ru.click.core.util.AutowireHelper;

import javax.persistence.PostPersist;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;

/**
 * <p>
 * Создан 14.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Slf4j
public class CreatedTrainingListener {

    private SimpMessageSendingOperations mesTemplate;

    private SmsWrapperService smsService;

    @PostPersist
    private void postToCalendar(Training training) {
        try {
            AutowireHelper.autowire(this, this.mesTemplate);
            AutowireHelper.autowire(this, this.smsService);
            LocalDateTime start = training.getStart();
            LocalDateTime end = start.plus(30L, MINUTES);
            CalendarEvent busyEvent = CalendarEvent
                    .createEvent()
                    .id(training.getId())
                    .title("Занято")
                    .start(start)
                    .end(end);
            mesTemplate.convertAndSend("/calendar/event", busyEvent);
            // TODO: 16.12.2016 поменять текст сообщения
            smsService.send(training.getClient().getPhone(), "Вы записаны на тренировку");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Autowired
    public void setMesTemplate(SimpMessageSendingOperations mesTemplate) {
        this.mesTemplate = mesTemplate;
    }

    @Autowired
    public void setSmsService(SmsWrapperService smsService) {
        this.smsService = smsService;
    }
}
