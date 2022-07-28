package me.saransh13.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessingResponse {
    private long requestId;
    private int processingCharge;
    private int packagingAndDeliveryCharge;
    private LocalDate dateOfDelivery;
}