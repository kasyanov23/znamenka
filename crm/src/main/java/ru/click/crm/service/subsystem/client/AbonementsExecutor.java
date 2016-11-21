package ru.click.crm.service.subsystem.client;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import ru.click.crm.represent.page.schedule.SubscriptionApi;
import ru.click.core.represent.impl.BaseExecutor;

import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.ZERO;
import static ru.click.core.model.QAbonement.abonement;
import static ru.click.core.model.QClientAbonement.clientAbonement;

/**
 * Создан 03.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class AbonementsExecutor extends BaseExecutor<Tuple, SubscriptionApi> implements AbonementsService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubscriptionApi> abonements(Long clientId) {
        List<Tuple> tuple = initSubScrQuery(clientId).fetch();
        return toApi(tuple);
    }

    /**
     * Инициализирует SQL запрос
     * @param clientId уникальный идентификатор клиента
     * @return запрос
     */
    private JPAQuery<Tuple> initSubScrQuery(Long clientId) {
        return getQuery()
                .select(clientAbonement.purchaseId, abonement.productName)
                .distinct()
                .from(clientAbonement)
                .innerJoin(clientAbonement.product, abonement)
                .where(clientAbonement.trainingCount.gt(ZERO).and(clientAbonement.clientId.eq(clientId)));

    }
}
