package ru.click.crm.represent.converter.page.schedule;


import com.querydsl.core.Tuple;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.click.crm.represent.page.schedule.SubscriptionApi;

/**
 * <p>
 * Создан 26.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Component
public class SubscriptionApiConverter implements Converter<Tuple, SubscriptionApi> {

    @Override
    public SubscriptionApi convert(Tuple tuple) {
        return SubscriptionApi
                .builder()
                .purchaseId(tuple.get(0, Long.class))
                .productName(tuple.get(1, String.class))
                .trainingCount(tuple.get(2, Integer.class))
                .build();
    }
}
