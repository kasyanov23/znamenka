package ru.click.crm.controller.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.click.reporting.model.BalanceTraining;
import ru.click.reporting.report.BalanceTrainingService;

import java.util.List;

@Controller
@RequestMapping("/reporting/balance-training")
public class BalanceTrainingController {

    private final BalanceTrainingService service;

    @Autowired
    public BalanceTrainingController(BalanceTrainingService service) {
        this.service = service;
    }

    @GetMapping("/data")
    @ResponseBody
    public List<BalanceTraining> data() {
        return service.balanceTrainings();
    }
}
