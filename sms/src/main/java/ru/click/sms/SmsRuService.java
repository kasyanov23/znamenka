package ru.click.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dezhik.sms.sender.SenderService;
import ru.dezhik.sms.sender.api.smsru.send.SMSRuSendRequest;
import ru.dezhik.sms.sender.api.smsru.send.SMSRuSendResponse;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Wrapper над сервисом отправки.
 * <p>
 * Создан 16.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
@Slf4j
public class SmsRuService implements SmsService {

    private final SenderService senderService;

    @Autowired
    public SmsRuService(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public String send(String phone, String text) {
        SMSRuSendRequest request = new SMSRuSendRequest();
        List<String> receivers = singletonList("+7" + phone);
        request.setReceivers(receivers);
        request.setText(text);
        SMSRuSendResponse response = senderService.execute(request);
        log.info(response.getPlainResponse());
        return response.getResponseStatus().toString();
    }
}
