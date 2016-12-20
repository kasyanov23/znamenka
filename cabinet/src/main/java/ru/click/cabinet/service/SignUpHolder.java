package ru.click.cabinet.service;

import ru.click.core.model.Client;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

/**
 * Держатель клиентов и кодов. Так как этап регистрации состоит
 * из нескольких этапов, необходимо хранить промежуточные данные на стороне сервера.
 * Для этого мы используем примитивный самоочищаемый кеш на основе {@link ConcurrentHashMap}
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
abstract class SignUpHolder {
    /**
     * Кеш для смс кодов, где ключом является номер телефона клиента
     */
    private static final Map<String, Integer> smsCodes = new ConcurrentHashMap<>();

    /**
     * Кеш для клиентов ({@link Client}), где ключом является номер телефона клиента
     */
    private static final Map<String, Client> clients = new ConcurrentHashMap<>();

    /**
     * Добавляет новый код в кеш. Если для такого телефона код уже существует, то
     * он заменяется новым значением
     *
     * @param phone номер телефона клиента
     * @param code  смс код
     */
    static void putCode(String phone, Integer code) {
        smsCodes.put(phone, code);
    }

    /**
     * Возвращает опциональное значение с смс кодом, удаляя его из кеша
     *
     * @param phone номер телефона клиента
     * @return опциональное значение с смс кодом
     */
    static Optional<Integer> getCode(String phone) {
        return ofNullable(smsCodes.remove(phone));
    }

    /**
     * Добавляет нового клиента в кеш. Если для такого телефона клиент уже существует, то
     * он заменяется новым значением
     *
     * @param phone  номер телефона клиента
     * @param client клиент ({@link Client})
     */
    static void putClient(String phone, Client client) {
        clients.put(phone, client);
    }

    /**
     * Возвращает опциональное значение с клиентом, удаляя его из кеша
     *
     * @param phone номер телефона клиента
     * @return опциональное значение с клиентом ({@link Client})
     */
    static Optional<Client> getClient(String phone) {
        return ofNullable(clients.remove(phone));
    }
}
