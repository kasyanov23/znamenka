package ru.click.crm.controller.page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ru.click.crm.represent.domain.DutyApi;
import ru.click.crm.represent.domain.PlanTypeApi;
import ru.click.core.represent.ApiStore;
import ru.click.crm.service.subsystem.training.EventService;
import ru.click.core.model.CalendarEvent;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.Assert.notNull;

@Controller
@RequestMapping("/report")
public class ReportController{



    @GetMapping
    public ModelAndView getSchedulePage() {
        ModelAndView mv = new ModelAndView("report");

        return mv;
    }


}
