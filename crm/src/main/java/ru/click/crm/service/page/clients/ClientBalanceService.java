package ru.click.crm.service.page.clients;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.click.crm.represent.page.ClientBalance;
import ru.click.core.represent.ApiStore;
import ru.click.core.model.Payment;
import ru.click.core.model.Purchase;
import ru.click.core.repository.EntityRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_HALF_EVEN;
import static ru.click.crm.util.BDFactory.bd;
import static ru.click.core.model.QPurchase.purchase;

@Service
@Slf4j
public class ClientBalanceService {

    @Autowired
    private EntityRepository repo;

    @Autowired
    @Qualifier("dataService")
    private ApiStore service;

    public List<ClientBalance> getClients() {
        List<ClientBalance> clientBalances = service.findAll(ClientBalance.class);
        return clientBalances.stream().map(c -> {
            c.setBalance(getBalance(c.getId()));
            return c;
        }).collect(Collectors.toList());
    }

    private double getBalance(Long clientId) {
        BigDecimal balance = bd(0);
        List<Purchase> purchases = repo.findAll(Purchase.class, purchase.client.id.eq(clientId));
        if (!purchases.isEmpty()) {
            for (Purchase purchase : purchases) {
                BigDecimal price = bd(purchase.getProduct().getPrice());
                val discount = purchase.getDiscount();
                if (discount != null) {
                    int amountDisc = discount.getDiscountAmount();
                    val mult = bd(100 - amountDisc).divide(bd(100), ROUND_HALF_EVEN);
                    price = price.multiply(mult);
                }
                balance = balance.subtract(price);

                List<Payment> payments = purchase.getPayments();
                if (payments != null && !payments.isEmpty()) {
                    for (Payment payment : payments) {
                        balance = balance.add(bd(payment.getPaymentAmount()));
                    }
                }
            }
            return balance.doubleValue();
        }
        return 0;
    }
}
