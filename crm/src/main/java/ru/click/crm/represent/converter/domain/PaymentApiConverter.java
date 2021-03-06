package ru.click.crm.represent.converter.domain;


import org.springframework.stereotype.Component;
import ru.click.crm.represent.domain.PaymentApi;
import ru.click.core.represent.converter.ApiConverter;
import ru.click.core.model.Payment;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

@Component
public class PaymentApiConverter implements ApiConverter<Payment, PaymentApi> {

    @Override
    public Class<PaymentApi> getApiType() {
        return PaymentApi.class;
    }

    @Override
    public Class<Payment> getEntityType() {
        return Payment.class;
    }

    // TODO: 25.08.2016
    @Override
    public Payment convertTo(PaymentApi source) {
        Payment p = new Payment();
        p.setPurchaseId(source.getPurchaseId());
        p.setPaymentAmount(source.getAmount());
        p.setPaymentDate(valueOf(LocalDateTime.now()));

        return p;
    }

    @Override
    public PaymentApi convert(Payment source) {
        PaymentApi api = new PaymentApi();
        api.setAmount(source.getPaymentAmount());
        api.setPurchaseId(source.getPurchaseId());
        api.setPaymentDate(source.getPaymentDate());
        return api;
    }
}
