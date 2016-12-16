package ru.click.reporting.model;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class SoldSubscriptions {

    private final String clientName;

    private final Date purchaseDate;

    private final String productName;

    private final Integer price;

    private final Integer payd;
}
