package ru.click.core.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.click.core.model.LogSms;
import ru.click.core.repository.domain.LogSmsRepository;
import ru.click.sms.SmsService;

import static org.springframework.util.Assert.hasText;

/**
 * Сервис-wrapper для реализации асинхронной отправки смс,
 * преобразующий телефонные номера, хранящиеся в базе к международному формату.
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
    @Async
    public void send(String phone, String text) {
        String checkedPhone = convertPhone(phone);
        String status = smsService.send(checkedPhone, text);
        val sms = new LogSms()
                .setPhone(checkedPhone.substring(1, checkedPhone.length()))
                .setText(text)
                .setStatus(status);
        repository.save(sms);
    }

    /**
     * Преобразует номер телфона в международный формат
     *
     * @param phoneFromClient исходный номер телефона
     * @return номер телефона в международном формате
     */
    private String convertPhone(String phoneFromClient) throws RuntimeException {
        hasText(phoneFromClient, "Номер телефона отсутствует");
        StringBuilder number = new StringBuilder("");
        char[] letters = phoneFromClient.toCharArray();
        for (char letter : letters) {
            if (Character.isDigit(letter)) {
                number = number.append(letter);
            }
        }

        int length = number.length();
        if (length == 10) {
            return number.insert(0, "+7").toString();
        }
        if (length == 11) {
            char firstChar = number.charAt(0);
            if (firstChar == '7') {
                return number.insert(0, '+').toString();
            }
            if (firstChar == '8') {
                return number.replace(0, 1, "+7").toString();
            }
            throw new RuntimeException("Неверный код страны");
        }
        throw new RuntimeException("Неверный номер телефона");
    }
}
