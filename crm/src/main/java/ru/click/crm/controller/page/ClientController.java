package ru.click.crm.controller.page;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.click.crm.annotation.ActionLogged;
import ru.click.crm.represent.domain.ClientApi;
import ru.click.crm.represent.domain.TrainingApi;
import ru.click.crm.represent.page.client.ClientPurchaseApi;
import ru.click.crm.represent.page.schedule.SubscriptionApi;
import ru.click.crm.service.subsystem.client.IClientFacadeService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.util.Assert.notNull;

/**
 * Контроллер клиента.
 * <p>
 * Адреса страниц:
 * /client - возвращает страницу Клиенты
 * API:
 * GET, /client/{id}                - возвращает клиента в формате json по его id
 * PUT, /client/                    - обновляет клиента.
 * POST, /client/                   - создает нового клиента
 * GET, /client/{id}/trainings      - возвращает тренировки по id клиента
 * GET, /client/{id}/purchases      - возвращает покупки клиента
 * GET, /client/search/phone        - возвращает клиента но номеру телефона
 * <p>
 * Создан 12.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Controller
@RequestMapping("/client")
@Validated
@Slf4j
public class ClientController {

    /**
     * Сервис для операций с клиентами
     */
    private final IClientFacadeService clientService;

    /**
     * Конструктор для внедрения зависимостей
     *
     * @param clientService сервис для операций с клиентами
     */
    @Autowired
    public ClientController(IClientFacadeService clientService) {
        notNull(clientService);
        this.clientService = clientService;
    }

    /**
     * Метод для инициализации страницы для операций с клиентами
     *
     * @return страницу templates/client.html
     */
    // TODO: 12.10.2016 ограничение для тренеров
    @GetMapping
    public ModelAndView index(@ModelAttribute Long trainerId) {
        List<ClientApi> clients = clientService.store().findAll(ClientApi.class); //.clientsByTrainerId(trainerId);
        ModelAndView mv = new ModelAndView("client");
        mv.addObject("clientNew", new ClientApi());
        mv.addObject("clients", clients);
        return mv;
    }

    /**
     * Возвращает клиента по его уникальному идентификатору
     *
     * @param id уникальный идентификатор клиента
     * @return клиент
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientApi> clientApi(@PathVariable Long id) {
        ClientApi client = clientService.store().findOne(ClientApi.class, id);
        return ok(client);
    }

    /**
     * Обновляет информацию о клиенте
     *
     * @param client        обновленный клиент
     * @param bindingResult результат валидации
     * @return обновленный клиент, либо код ответа 400, при ошибки валидации
     */
    @PutMapping
    @ActionLogged(action = "обновил информацию о клиенте")
    public ResponseEntity<ClientApi> updateClient(@Valid ClientApi client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return badRequest().body(client);
        }
        return ok(clientService.update(client));
    }

    /**
     * Сохраняет нового клиента
     *
     * @param clientNew     новый клиент
     * @param bindingResult результат валидации
     * @return редирект на страницу с клиентами
     */
    // TODO: 30.09.2016 если данные не валидны, то пользователю об этом не сообщается
    @PostMapping
    @ActionLogged(action = "добавил клиента")
    public ResponseEntity<ClientApi> createClient(@Valid ClientApi clientNew, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
                return badRequest().body(clientNew);
        }
        long id = clientService.store().save(ClientApi.class, clientNew);
        return ok(clientNew.setId(id));
    }

    /**
     * Возвращает список тренировок клиента.
     * Используется для вкладки Тренировки в карточке клиента
     *
     * @param id уникальный идентификатор клиента
     * @return список тренировок
     */
    @GetMapping("/{id}/trainings")
    public ResponseEntity<?> getTrainingsByClient(@PathVariable Long id) {
        List<TrainingApi> trainings = clientService.trainings(id);
        if (trainings == null) return noContent().build();
        return ok(trainings);
    }

    /**
     * Возвращает список покупок клиентов и дополнительную информацию,
     * сколько осталось заплатить, сколько заплачено, etc.
     *
     * @param id уникальный идентификатор клиента
     * @return список покупок
     * @see ClientPurchaseApi
     */
    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<ClientPurchaseApi>> getPurchasesByClient(@PathVariable Long id) {
        List<ClientPurchaseApi> purchases = clientService.purchases(id);
        return ok(purchases);
    }

    /**
     * Поиск клиента по номеру телефона
     *
     * @param phone номер телефона
     * @return код 200 (и клиента) если клиент найден и 204 если не найден
     */
    @GetMapping("/search/phone")
    public ResponseEntity<?> searchClientByPhone(@Pattern(regexp = "^7[0-9]{10}") String phone
    ) {

        ClientApi clientApi = clientService.clientByPhone(phone);
        if (clientApi == null) {
            return noContent().build();
        }
        return ok(clientApi);
    }

    /**
     * API для подгрузки абонементов клиента по его id
     *
     * @param clientId уникальный идентификатор клиента
     * @return список абонементов
     */
    @GetMapping("/abonements")
    public ResponseEntity<List<SubscriptionApi>> getSubscriptions(@RequestParam("clientId") Long clientId) {
        if (clientId == null) {
            return badRequest().body(emptyList());
        }
        List<SubscriptionApi> subscriptions = clientService
                .abonements(clientId)
                .stream()
                .filter(ss -> clientService.existsFreeTrainings(clientId, ss.getPurchaseId()))
                .collect(toList());
        return ok(subscriptions);
    }

    /**
     * Метод для обработки ситуации, когда валидируемый параметр метод не валиден, например неверный
     * формат номера телефона
     *
     * @return код ответа 400
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity handleValidationEx() {
        return badRequest().build();
    }
}
