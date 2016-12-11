package ru.click.crm.controller.reporting;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.click.core.represent.ApiStore;
import ru.click.crm.represent.domain.ProductApi;
import ru.click.reporting.model.SoldSubscriptions;
import ru.click.reporting.report.SoldSubscriptionsService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reporting/sold-subscriptions")
public class SoldSubscriptionsController {

    @Autowired
    private SoldSubscriptionsService service;

    @Autowired
    @Qualifier("convertService")
    private ApiStore apiStore;

    @GetMapping("/data")
    @ResponseBody
    public List<SoldSubscriptions> data(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate to,
            @RequestParam("product_id") Long productId
    ) {
        return service.soldSubscriptions(from, to, productId);
    }

    @GetMapping("/filter")
    public ModelAndView filter() {
        val mv = new ModelAndView("reporting/sold-subscriptions :: sold-subscription-filter");
        mv.addObject("products", apiStore.findAll(ProductApi.class));
        return mv;
    }

}
