package ru.click.crm.service.subsystem.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.click.crm.represent.domain.TrainingApi;
import ru.click.crm.represent.page.client.ClientPurchaseApi;
import ru.click.crm.represent.page.schedule.SubscriptionApi;
import ru.click.crm.represent.domain.ClientApi;
import ru.click.core.represent.ApiStore;

import java.util.List;

import static org.springframework.util.Assert.notNull;
import static ru.click.core.model.QClient.client;
import static ru.click.core.model.QPurchase.purchase;
import static ru.click.core.model.QTraining.training;

/**
 * Создан 29.09.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class ClientFacadeService implements IClientFacadeService {

    private final ApiStore service;

    private final ClientsQueryService clientExecutor;

    private final AbonementsService abonementsService;

    @Autowired
    public ClientFacadeService(
            @Qualifier("dataService") ApiStore service,
            ClientsQueryService clientExecutor,
            AbonementsService abonementsService
    ) {
        notNull(service);
        notNull(clientExecutor);
        notNull(abonementsService);
        this.service = service;
        this.clientExecutor = clientExecutor;
        this.abonementsService = abonementsService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiStore store() {
        return service;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientApi> allClients() {
        return service.findAll(ClientApi.class);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public ClientApi update(ClientApi upClient) {
        ClientApi clientApi = service.findOne(ClientApi.class, upClient.getId());
        clientApi.setFname(upClient.getFname());
        clientApi.setSname(upClient.getSname());
        clientApi.setBirthDate(upClient.getBirthDate());
        clientApi.setPhone(upClient.getPhone());
        clientApi.setEmail(upClient.getEmail());
        clientApi.setComment(upClient.getComment());
        clientApi.setMale(upClient.getMale());
        return service.update(ClientApi.class, clientApi);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrainingApi> trainings(Long clientId) {
        return service.findAll(TrainingApi.class, training.clientId.eq(clientId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientPurchaseApi> purchases(Long clientId) {
        return service.findAll(ClientPurchaseApi.class, purchase.client.id.eq(clientId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientApi clientByPhone(String phone) {
        return service.findOne(ClientApi.class, client.phone.eq(phone));
    }

    @Override
    public boolean existsFreeTrainings(Long clientId, Long purchaseId) {
        return abonementsService.existsFreeTrainings(clientId, purchaseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubscriptionApi> abonements(Long clientId) {
        return abonementsService.abonements(clientId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientApi> activeClients() {
        return clientExecutor.activeClients();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientApi> clientsByTrainerId(Long trainerId) {
        return clientExecutor.clientsByTrainerId(trainerId);
    }
}
