package ru.click.cabinet.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.click.cabinet.exception.DataAccessSignUpException;
import ru.click.cabinet.exception.NoExistsClientSignUpException;
import ru.click.cabinet.exception.UnknownSignUpException;
import ru.click.cabinet.exception.WrongCodeSignUpException;
import ru.click.core.model.Client;
import ru.click.core.model.LkUser;
import ru.click.core.model.QClient;
import ru.click.core.repository.domain.ClientRepository;
import ru.click.core.repository.domain.LkUserRepository;
import ru.click.core.service.SmsWrapperService;

import javax.validation.constraints.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@Validated
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

    public Client sendSms(
            @Pattern(regexp = "^9[0-9]{9}$", message = "{phone.pattern}")
                    String phone
    ) {
        Client client = clientRepository.findOne(QClient.client.phone.eq("7".concat(phone)));
        if (client == null) {
            throw new NoExistsClientSignUpException();
        }
        Integer code = ThreadLocalRandom.current().nextInt(1000, 9999);
        SignUpHolder.putCode(phone, code);
        SignUpHolder.putClient(phone, client);
        smsService.send(phone, String.valueOf(code));
        return client;
    }

    public void sendSmsAgain(
            @Pattern(regexp = "^9[0-9]{9}$", message = "{phone.pattern}")
            @NotEmpty(message = "{unknown.sign.up.error}") String phone
    ) {
        Integer code = ThreadLocalRandom.current().nextInt(1000, 9999);
        SignUpHolder.putCode(phone, code);
        smsService.send(phone, String.valueOf(code));
    }

    public void verify(@Pattern(regexp = "^9[0-9]{9}$", message = "{phone.pattern}") String phone,
                       @Min(value = 1000, message = "{code.length}")
                       @Max(value = 9999, message = "{code.length}") Integer code
    ) {
        if (!checkCode(phone, code)) {
            throw new WrongCodeSignUpException();
        }
    }

    public void confirm(
            String phone,
            @Length(min = 6, max = 16, message = "{password.length}")
            @Pattern(regexp = "^[A-z]+[0-9A-z]+$", message = "{password.pattern}") String password,
            @AssertTrue(message = "{password.not.equals}") boolean passwordEquals
    ) {
        Client client = SignUpHolder.getClient(phone).orElseThrow(UnknownSignUpException::new);
        val user = new LkUser();
        user.setUsername(phone);
        user.setPassword(passwordEncoder.encode(password));
        user.setClient(client);
        user.setName(client.getName());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DataAccessSignUpException();
        }
    }

    private boolean checkCode(String phone, int code) {
        val earlyCode = SignUpHolder.getCode(phone);
        return earlyCode.orElseThrow(UnknownSignUpException::new).equals(code);
    }

}
