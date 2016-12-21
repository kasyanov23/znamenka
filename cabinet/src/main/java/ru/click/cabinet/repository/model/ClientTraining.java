package ru.click.cabinet.repository.model;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Проведенные тренировки клиента
 * <p>
 * Создан 21.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Getter
public class ClientTraining {

    private final Studio studio;

    private final LocalDateTime start;

    private final String trainerName;

    private final String trainingStatus;

    private final String productName;

    private ClientTraining(
            Studio studio,
            LocalDateTime start,
            String trainerName,
            String trainingStatus,
            String productName
    ) {
        this.studio = studio;
        this.start = start;
        this.trainerName = trainerName;
        this.trainingStatus = trainingStatus;
        this.productName = productName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Studio studio;
        private LocalDateTime start;
        private String trainerName;
        private String trainingStatus;
        private String productName;

        private Builder() {
        }

        public Builder studio(int codeStudio) {
            this.studio = Studio.from(codeStudio);
            return this;
        }

        public Builder start(Timestamp start) {
            this.start = start.toLocalDateTime();
            return this;
        }

        public Builder trainerName(String trainerName) {
            this.trainerName = trainerName;
            return this;
        }

        public Builder trainingStatus(String trainingStatus) {
            this.trainingStatus = trainingStatus;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ClientTraining build() {
            return new ClientTraining(studio, start, trainerName, trainingStatus, productName);
        }
    }
}
