package ru.click.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.click.core.model.LogSms;
import ru.click.core.repository.domain.LogSmsRepository;
import ru.click.sms.SmsService;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SmsLoggingServiceTest {

    @Mock
    private LogSmsRepository mockLogSmsRepository;

    @Mock
    private SmsService mockSmsService;

    @InjectMocks
    private SmsLoggingService smsLoggingService;

    private final LogSms testSms = new LogSms()
            .setPhone("79851234567")
            .setText("Hi")
            .setStatus("Ok");

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(mockSmsService.send(eq("+79851234567"), eq("Hi"))).thenReturn("Ok");
        when(mockSmsService.send(eq("4532rrf"), anyString())).thenThrow(new RuntimeException());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void send() throws Exception {
        smsLoggingService.send("+79851234567", "Hi");
        verify(mockSmsService).send(eq("+79851234567"), eq("Hi"));
        verify(mockLogSmsRepository).save(eq(testSms));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exception() throws Exception {
        smsLoggingService.send("4532rrf", "Hi");

    }
}