package ru.click.cabinet.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SmsCodeHolder {

    private static final Map<String, Integer> smsCodes = new ConcurrentHashMap<>();

    public static void put(String phone, Integer code) {
        smsCodes.put(phone, code);
    }

    public static Integer getCode(String phone) {
        return smsCodes.get(phone);
    }
}
