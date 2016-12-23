package ru.click.cabinet.service;

import ru.click.core.model.Client;

/**
 * Интерфейс сервиса для регистрации клиента в нашей системе
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface ISignUpService {

    /**
     * Проверяет наличие телефона в базе, если телефон присутствует,
     * то отправляет ему смс с 4-х кодом. Иначе - бросает исключение.
     *
     * @param phone номер телефона клиента
     * @return клиент
     */
    Client sendSms(String phone);

    /**
     * Отправляет смс клиенту повторно
     *
     * @param phone номер телефона клиента
     */
    void sendSmsAgain(String phone);

    /**
     * Проверяет, соотстветствует ли код из смс отправленному ранее коду
     *
     * @param phone номер телефона клиента
     * @param code  код, который ввел клиент
     */
    void verify(String phone, Integer code);

    /**
     * Подтверждает регистрацию и создает нового пользователя
     *
     * @param phone          номер телефона клиента
     * @param password       пароль клиента
     * @param passwordEquals флаг, показывающий, соответствует ли пароль и подтверждающий пароль друг другу
     */
    void confirm(String phone, String password, boolean passwordEquals);
}
