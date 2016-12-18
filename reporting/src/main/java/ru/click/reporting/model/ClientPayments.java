package ru.click.reporting.model;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class ClientPayments {
    
    private final String clientName;
    
    private final Date paymentDate;
    
    private final Long sumPayments;

    private final Long leftToAdd;
    
    private final String productName;
    
    private final String trainerName;
}
