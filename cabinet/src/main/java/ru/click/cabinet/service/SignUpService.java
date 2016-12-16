package ru.click.cabinet.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.click.cabinet.exception.NoExistsClientSignUpException;
import ru.click.cabinet.exception.WrongCodeSignUpException;
import ru.click.core.model.Client;
import ru.click.core.model.LkUser;
import ru.click.core.model.QClient;
import ru.click.core.repository.domain.ClientRepository;
import ru.click.core.repository.domain.LkUserRepository;
import ru.click.core.service.SmsWrapperService;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SignUpService {

    private final SmsWrapperService smsService;

    private final LkUserRepository userRepository;

    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(
            SmsWrapperService smsService,
            LkUserRepository userRepository,
            ClientRepository clientRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.smsService = smsService;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendSms(String phone) {
        Integer code = ThreadLocalRandom.current().nextInt(1000, 9999);
        SmsCodeHolder.put(phone, code);
        smsService.send(phone, String.valueOf(code));
    }

    public void signUp(String phone, int code) {
        if (!checkCode(phone, code)) {
            throw new WrongCodeSignUpException();
        }

        Client client = clientRepository.findOne(QClient.client.phone.eq(phone));
        if (client == null) {
            throw new NoExistsClientSignUpException();
        }
        String randomPassword = new BigInteger(130, ThreadLocalRandom.current()).toString(32);
        smsService.send(phone, "password: " + randomPassword);
        val user = new LkUser();
        user.setUsername(phone);
        user.setPassword(passwordEncoder.encode(randomPassword));
        user.setClient(client);
        userRepository.save(user);
    }


    private boolean checkCode(String phone, int code) {
        int earlyCode = SmsCodeHolder.getCode(phone);
        return earlyCode == code;
    }
}
