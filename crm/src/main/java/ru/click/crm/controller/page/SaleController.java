package ru.click.crm.controller.page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.click.crm.represent.domain.*;
import ru.click.crm.represent.page.sale.ClientDebtApi;
import ru.click.core.represent.ApiStore;
import ru.click.crm.service.page.sale.SalePageService;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.util.Assert.notNull;
import static ru.click.crm.represent.domain.PurchaseApi.emptyPurchase;

/**
 * <p>
 * <p>
 * Создан 12.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Controller
@RequestMapping("/sale")
public class SaleController {

    /**
     * Хранилище представлений
     */
    private final ApiStore apiStore;

    /**
     * Сервис для продаж
     */
    private final SalePageService saleService;

    /**
     * Конструктор, возвращающий экземпляр контроллера с
     * необходимыми зависимостями
     * @param apiStore хранилище представлений
     * @param saleService сервис продаж
     */
    @Autowired
    public SaleController(@Qualifier("dataService") ApiStore apiStore, SalePageService saleService) {
        notNull(apiStore);
        notNull(saleService);
        this.apiStore = apiStore;
        this.saleService = saleService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("sale");
        List<ClientApi> clients = apiStore.findAll(ClientApi.class);
        List<ProductApi> products = apiStore.findAll(ProductApi.class);
        List<TrainerApi> trainers = apiStore.findAll(TrainerApi.class);
        List<DiscountApi> discounts = apiStore.findAll(DiscountApi.class);

        mv.addObject("clients", clients);
        mv.addObject("products", products);
        mv.addObject("trainers", trainers);
        mv.addObject("discounts", discounts);
        mv.addObject("purchase", emptyPurchase());
        return mv;
    }

    @PostMapping
    public RedirectView addPurchase(@Valid PurchaseApi purchase, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            purchase.setPurchaseDate(Date.valueOf(LocalDate.now()));
            apiStore.save(PurchaseApi.class, purchase);
        }
        return new RedirectView("/sale");
    }

    @GetMapping("/purchases")
    public ModelAndView getPurchases(@RequestParam("clientId") Long clientId) {
        ModelAndView mv = new ModelAndView("sale :: purchases-table");
        if (clientId == null) {
            mv.addObject("payments", emptyList());
            return mv;
        }

        List<ClientDebtApi> payments = saleService.getClientPayments(clientId);
        mv.addObject("payments", payments);
        mv.addObject("payment", new PaymentApi());
        mv.addObject("clientId", clientId);
        return mv;
    }

    @PostMapping("/payment")
    public RedirectView addPayments(@Valid PaymentApi payment, Double remain, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && payment.getAmount() <= remain) {
            apiStore.save(PaymentApi.class, payment);
        }
        return new RedirectView("/sale");
    }


}
