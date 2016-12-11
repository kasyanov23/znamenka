package ru.click.crm.controller.reporting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

    @GetMapping
    public String view() {
        return "reporting/reports";
    }
}
