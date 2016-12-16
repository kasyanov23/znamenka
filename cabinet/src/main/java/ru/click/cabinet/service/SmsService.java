package ru.click.cabinet.service;

public interface SmsService {

    void send(String phone, String text);
}
