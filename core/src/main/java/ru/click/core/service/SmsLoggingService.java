package ru.click.core.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.click.core.model.LogSms;
import ru.click.core.repository.domain.LogSmsRepository;
import ru.click.sms.SmsService;

/**
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class SmsLoggingService implements SmsWrapperService {

    private final LogSmsRepository repository;

    private final SmsService smsService;

    @Autowired
    public SmsLoggingService(LogSmsRepository repository, SmsService smsService) {
        this.repository = repository;
        this.smsService = smsService;
    }

    @Override
    public void send(String phone, String text) {
        String status = smsService.send(phone, text);
        val sms = new LogSms()
                .setPhone(phone)
                .setText(text)
                .setStatus(status);
        repository.save(sms);
    }
}
