package ru.click.cabinet.service;

import ru.click.core.model.Client;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

public abstract class SignUpHolder {

    private static final Map<String, Integer> smsCodes = new ConcurrentHashMap<>();

    private static final Map<String, Client> clients = new ConcurrentHashMap<>();

    public static void putCode(String phone, Integer code) {
        smsCodes.put(phone, code);
    }

    public static Optional<Integer> getCode(String phone) {
        return ofNullable(smsCodes.remove(phone));
    }

    public static void putClient(String phone, Client client) {
        clients.put(phone, client);
    }

    public static Optional<Client> getClient(String phone) {
        return ofNullable(clients.remove(phone));
    }
}
