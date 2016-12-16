package ru.click.sms;

/**
 * Wrapper над сервис отправки смс {@link ru.dezhik.sms.sender.SenderService}
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface SmsService {
    /**
     * Отправляет смс сообщение на указанные номер
     *
     * @param phone телефон получателя в формате 10 цифр (без +7 или 8) - 9850625571
     * @param text  текст сообщения
     * @return статус отправки
     */
    String send(String phone, String text);
}
