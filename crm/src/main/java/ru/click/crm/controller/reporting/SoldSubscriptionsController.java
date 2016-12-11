package ru.click.crm.controller.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.click.reporting.model.SoldSubscriptions;
import ru.click.reporting.report.SoldSubscriptionsService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reporting/sold-subscriptions")
public class SoldSubscriptionsController {

    @Autowired
    private SoldSubscriptionsService service;

    @GetMapping("/data")
    @ResponseBody
    public List<SoldSubscriptions> data(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate to,
            @RequestParam("product_id") Long productId
    ) {
        return service.soldSubscriptions(from, to, productId);
    }

}
