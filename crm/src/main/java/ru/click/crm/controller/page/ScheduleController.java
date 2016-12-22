package ru.click.crm.controller.page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.click.core.model.CalendarEvent;
import ru.click.crm.represent.domain.ClientApi;
import ru.click.crm.represent.domain.TrainerApi;
import ru.click.crm.represent.domain.TrainingApi;
import ru.click.crm.service.subsystem.client.IClientFacadeService;
import ru.click.crm.service.subsystem.training.EventService;
import ru.click.crm.service.subsystem.training.ITrainingService;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.util.Assert.notNull;

@Controller
@RequestMapping("/training")
public class ScheduleController {

    private final ITrainingService service;

    private final EventService eventService;

    private final IClientFacadeService clientService;

    @Autowired
    public ScheduleController(
            EventService eventService,
            ITrainingService service,
            IClientFacadeService clientService) {
        notNull(eventService);
        notNull(service);
        notNull(clientService);
        this.eventService = eventService;
        this.service = service;
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainingById(@PathVariable Long id) {
        TrainingApi training = clientService.store().findOne(TrainingApi.class, id);
        if (training == null) {
            return noContent().build();
        }
        return ok(training);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TrainingApi> updateTraining(@PathVariable Long id, Long statusId, Long trainerId) {
        return ok(service.updateTraining(statusId, id, trainerId));
    }

    /**
     * Метод для инициализации страницы "Запись на тренировку".
     * Загружает список клиентов, у которых есть активные абонементы
     *
     * @return страница templates/schedule.html
     */
    @GetMapping
    public ModelAndView getSchedulePage() {
        ModelAndView mv = new ModelAndView("schedule");
        List<ClientApi> clients = clientService.activeClients();
        List<TrainerApi> trainers = clientService.store().findAll(TrainerApi.class);
        mv.addObject("clientNew", ClientApi.empty());
        mv.addObject("clients", clients);
        mv.addObject("training", TrainingApi.empty());
        mv.addObject("trainers", trainers);
        return mv;
    }

    /**
     * API для бронивания тренировки для пользователя.
     * Тренировка записывается в базу со статусом "Зарезервирована" и номером
     * тренера.
     * <p>Далее, тренировка постится в гугль календарь и затем по веб сокету
     * постится в раписание студии (смотри {@link CalendarController}
     *
     * @param training      параметры тренировки
     * @param bindingResult результат валидации
     * @return 200 если тренировка успешно забронирована, 400 в ином случае
     */
    @PostMapping(path = "club-client")
    public ResponseEntity<TrainingApi> bookTraining(@Valid TrainingApi training, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            training = service.save(training);
            return ok(training);
        }
        return badRequest().body(training);
    }


    @PostMapping(path = "new-client")
    public ResponseEntity<TrainingApi> bookTraining(
            @Valid ClientApi client,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime start,
            @RequestParam Long trainerId,
            @RequestParam(required = false) Boolean passForAuto,
            @RequestParam(required = false) String comment,
            BindingResult br
    ) {
        if (br.hasErrors()) return badRequest().body(TrainingApi.empty());
        Long clientId = clientService.store().save(ClientApi.class, client);
        TrainingApi training = new TrainingApi();
        training.setClientId(clientId);
        training.setStart(start);
        training.setTrainerId(trainerId);
        training.setStatusId(1L);
        training.setComment(comment);
        training.setPassForAuto(passForAuto);
        service.save(training);
        return ok(training);
    }

    /**
     * API, которое является event-source для full-calendar'я на фронт енде.
     * Получает список событий и отдает на фронт-енд с фильтром по дате
     *
     * @param start начальная дата поиска событий
     * @param end   конечная дата
     * @return список событий
     */
    @GetMapping(path = "/events", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalendarEvent>> events(Date start, Date end) {
        return ok(eventService.loadEvents(start, end));
    }

    /**
     * API для общедоступного расписания студии с фильтром по дате
     *
     * @param start начальная дата поиска событий
     * @param end   конечная дата
     * @return список событий
     */
    @GetMapping(path = "/events/busy", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalendarEvent>> eventsBusy(Date start, Date end) {
        return ok(eventService.loadEventsBusy(start, end));
    }


}
