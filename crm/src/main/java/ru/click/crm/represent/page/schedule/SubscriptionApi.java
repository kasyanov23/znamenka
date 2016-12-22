package ru.click.crm.represent.page.schedule;


import lombok.Builder;
import lombok.Getter;
import ru.click.core.represent.DomainApi;

/**
 * <p>
 * Создан 26.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Builder
@Getter
public class SubscriptionApi implements DomainApi {

    private Long purchaseId;

    private String productName;

    private Integer trainingCount;
}
