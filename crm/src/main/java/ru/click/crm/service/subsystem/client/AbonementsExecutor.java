package ru.click.crm.service.subsystem.client;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import ru.click.core.represent.impl.BaseExecutor;
import ru.click.crm.represent.page.schedule.SubscriptionApi;

import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.ZERO;
import static ru.click.core.model.QAbonement.abonement;
import static ru.click.core.model.QClientAbonement.clientAbonement;
import static ru.click.core.model.QTraining.training;

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

    @Override
    public boolean existsFreeTrainings(Long clientId, Long purchaseId) {
        Integer paidTrainingCount = getQuery()
                .select(abonement.trainingCount.sum().coalesce(ZERO))
                .from(clientAbonement)
                .innerJoin(clientAbonement.product, abonement)
                .where(clientAbonement.purchaseId.eq(purchaseId)
                        .and(clientAbonement.clientId.eq(clientId)))
                .fetchOne();
        Integer trainingCount = getQuery()
                .select(training.id.count())
                .from(training)
                .where(training.statusId.in(1, 2, 4))
                .fetchOne()
                .intValue();
        return paidTrainingCount > trainingCount;
    }

    /**
     * Инициализирует SQL запрос
     *
     * @param clientId уникальный идентификатор клиента
     * @return запрос
     */
    private JPAQuery<Tuple> initSubScrQuery(Long clientId) {
        return getQuery()
                .select(clientAbonement.purchaseId, abonement.productName, clientAbonement.trainingCount)
                .distinct()
                .from(clientAbonement)
                .innerJoin(clientAbonement.product, abonement)
                .where(clientAbonement.trainingCount.gt(ZERO).and(clientAbonement.clientId.eq(clientId)));

    }
}
