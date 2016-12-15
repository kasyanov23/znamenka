package ru.click.crm.controller.empty_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.click.reporting.model.BalanceTraining;
import ru.click.reporting.report.BalanceTrainingService;

import java.util.List;

/**
 * Created by Сережа on 15.12.2016.
 */
public class Empty_template_controller {



    @GetMapping("/empty_template")
    public String paper() {
        return "NEW_TEMPLATE";
    }
}