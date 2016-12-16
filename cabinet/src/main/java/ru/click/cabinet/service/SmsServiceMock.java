package ru.click.cabinet.service;

import org.springframework.stereotype.Service;

@Service
public class SmsServiceMock implements SmsService {
    @Override
    public void send(String phone, String text) {

    }
}
