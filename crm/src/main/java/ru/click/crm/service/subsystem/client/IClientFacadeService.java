package ru.click.crm.service.subsystem.client;


import ru.click.core.represent.ApiStore;
import ru.click.crm.represent.domain.ClientApi;
import ru.click.crm.represent.domain.TrainingApi;
import ru.click.crm.represent.page.client.ClientPurchaseApi;

import java.util.List;

/**
 * Создан 29.09.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface IClientFacadeService extends AbonementsService, ClientsQueryService {

    /**
     * Позволяет обращаться к хранилищу данных для простых методов,
     * таких как findOne(id), save(api), etc
     *
     * @return ссылку на хранилище данных
     */
    ApiStore store();

    /**
     * Возвращает всех клиентов
     *
     * @return список клиентов
     */
    List<ClientApi> allClients();

    /**
     * Обновляет информацию о клиенте
     *
     * @param upClient обновленный клиент
     * @return обновленный клиент
     */
    ClientApi update(ClientApi upClient);

    /**
     * Поиск тренировок клиента
     *
     * @param clientId уникальный идентификатор клиента
     * @return список тренировок
     */
    List<TrainingApi> trainings(Long clientId);

    /**
     * Поиск покупок клиента
     *
     * @param clientId уникальный идентификатор клиента
     * @return список покупок
     */
    List<ClientPurchaseApi> purchases(Long clientId);

    /**
     * Поиск клиента по номеру телефона
     *
     * @param phone номер телефона
     * @return клиент
     */
    ClientApi clientByPhone(String phone);
}
