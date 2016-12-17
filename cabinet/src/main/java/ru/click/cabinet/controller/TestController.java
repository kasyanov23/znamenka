package ru.click.cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.click.cabinet.service.TrainingService;
import ru.click.core.model.CalendarEvent;

import java.sql.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TestController {

    @Autowired
    private TrainingService service;

    @GetMapping(path = "/events", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalendarEvent>> events(@RequestParam Date start,@RequestParam Date end) {
        // TODO: 21.11.2016 replace on clientId
        return ok(service.loadEvents(start, end, 27L));
    }

}
