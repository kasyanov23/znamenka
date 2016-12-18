package ru.click.reporting.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BalanceTraining {

    private final String clientName;

    private final String productName;

    private final Integer trainingCount;
}
