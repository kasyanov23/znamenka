package ru.click.cabinet.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.click.cabinet.exception.signup.DataAccessSignUpException;
import ru.click.cabinet.exception.signup.NoExistsClientSignUpException;
import ru.click.cabinet.exception.signup.UnknownSignUpException;
import ru.click.cabinet.exception.signup.WrongCodeSignUpException;
import ru.click.core.model.Client;
import ru.click.core.model.LkUser;
import ru.click.core.model.QClient;
import ru.click.core.repository.domain.ClientRepository;
import ru.click.core.repository.domain.LkUserRepository;
import ru.click.core.service.SmsWrapperService;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Сервис для регистрации клиента в нашей системе
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
@Slf4j
@Validated
public class SignUpService implements ISignUpService {
    /**
     * Смс сервис
     */
    private final SmsWrapperService smsService;

    /**
     * Репозиторий пользователей
     */
    private final LkUserRepository userRepository;
    /**
     * Репозиторий клиентов
     */
    private final ClientRepository clientRepository;

    /**
     * Шифровщик паролей
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для внедрения зависимостей
     *
     * @param smsService       смс сервис
     * @param userRepository   репозиторий пользователей
     * @param clientRepository репозиторий клиентов
     * @param passwordEncoder  шифровщик паролей
     */
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

    /**
     * Проверяет наличие телефона в базе, если телефон присутствует,
     * то отправляет ему смс с 4-х кодом. Иначе - бросает исключение.
     *
     * @param phone номер телефона клиента
     * @return клиент
     */
    @Override
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

    /**
     * Отправляет смс клиенту повторно
     *
     * @param phone номер телефона клиента
     */
    @Override
    public void sendSmsAgain(
            @Pattern(regexp = "^9[0-9]{9}$", message = "{phone.pattern}")
            @NotEmpty(message = "{unknown.sign.up.error}") String phone
    ) {
        Integer code = ThreadLocalRandom.current().nextInt(1000, 9999);
        SignUpHolder.putCode(phone, code);
        smsService.send(phone, String.valueOf(code));
    }

    /**
     * Проверяет, соотстветствует ли код из смс отправленному ранее коду
     *
     * @param phone номер телефона клиента
     * @param code  код, который ввел клиент
     */
    @Override
    public void verify(@Pattern(regexp = "^9[0-9]{9}$", message = "{phone.pattern}") String phone,
                       @Min(value = 1000, message = "{code.length}")
                       @Max(value = 9999, message = "{code.length}") Integer code
    ) {
        if (!checkCode(phone, code)) {
            throw new WrongCodeSignUpException();
        }
    }

    /**
     * Подтверждает регистрацию и создает нового пользователя
     *
     * @param phone          номер телефона клиента
     * @param password       пароль клиента
     * @param passwordEquals флаг, показывающий, соответствует ли пароль и подтверждающий пароль друг другу
     */
    @Override
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

    /**
     * Метод для проверки кодов из смс на равенство
     *
     * @param phone номер телефона клиента
     * @param code  код, введенный клиентом
     * @return {@literal true}, если коды равны, иначе - {@literal false}
     */
    private boolean checkCode(String phone, int code) {
        val earlyCode = SignUpHolder.getCode(phone);
        return earlyCode.orElseThrow(UnknownSignUpException::new).equals(code);
    }

}
