package ru.click.core.service;

/**
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface SmsWrapperService {

    void send(String phone, String text);
}
