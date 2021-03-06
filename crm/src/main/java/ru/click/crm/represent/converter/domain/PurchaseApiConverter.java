package ru.click.crm.represent.converter.domain;


import org.springframework.stereotype.Component;
import ru.click.core.represent.converter.ApiConverter;
import ru.click.crm.represent.domain.PurchaseApi;
import ru.click.core.model.Purchase;

/**
 * <p>
 * <p>
 * Создан 22.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Component
public class PurchaseApiConverter implements ApiConverter<Purchase, PurchaseApi> {

    @Override
    public Class<Purchase> getEntityType() {
        return Purchase.class;
    }

    @Override
    public Purchase convertTo(PurchaseApi source) {
        Purchase purchase = new Purchase();
        purchase.setProductId(source.getProductId());
        purchase.setClientId(source.getClientId());
        purchase.setDiscountId(source.getDiscountId());
        purchase.setTrainerId(source.getTrainerId());
        purchase.setPurchaseDate(source.getPurchaseDate());

        return purchase;
    }

    @Override
    public PurchaseApi convert(Purchase source) {
        PurchaseApi purchase = new PurchaseApi();
        purchase.setId(source.getId());
        purchase.setProductId(source.getProductId());
        purchase.setClientId(source.getClientId());
        purchase.setDiscountId(source.getDiscountId());
        purchase.setTrainerId(source.getTrainerId());
        purchase.setPurchaseDate(source.getPurchaseDate());
        return purchase;
    }

    @Override
    public Class<PurchaseApi> getApiType() {
        return PurchaseApi.class;
    }
}
