package ru.click.cabinet.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.click.cabinet.repository.model.ClientTraining;
import ru.click.core.model.Training;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Абстрактный класс с мапперами
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
abstract class Mappers {

    /**
     * Маппер для тренировки ({@link Training})
     */
    final static RowMapper<Training> training = (rs, i) -> new Training()
            .setClientId(rs.getLong("client_id"))
            .setTrainerId(rs.getLong("trainer_id"))
            .setPurchaseId(rs.getLong("purchase_id"))
            .setStart(rs.getTimestamp("start").toLocalDateTime())
            .setStatusId(rs.getLong("status_id"))
            .setComment(rs.getString("comment"))
            .setPassForAuto(rs.getBoolean("pass_for_auto"));

    /**
     * Маппер для тренировок клиента ({@link ClientTraining})
     */
    final static RowMapper<ClientTraining> clientTraining = (rs, i) -> ClientTraining
            .builder()
            .studio(rs.getInt(1))
            .start(rs.getTimestamp(2))
            .trainerName(rs.getString(3))
            .trainingStatus(rs.getString(4))
            .productName(rs.getString(5))
            .build();

    /**
     * Маппер для баланса тренировок, то есть разность
     * количества купленных тренировок к проведенным
     */
    final static RowMapper<Optional<Integer>> balanceOfTraining = (rs, i) -> {
        if (rs.wasNull()) {
            return empty();
        }
        return of(rs.getInt(1));
    };
}
