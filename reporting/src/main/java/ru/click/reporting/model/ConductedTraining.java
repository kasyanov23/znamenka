package ru.click.reporting.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * Создан 14.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Getter
@Builder
public class ConductedTraining {

    private final String clientName;
    private final LocalDate date;
    private final LocalTime time;
    private final String status;
}
