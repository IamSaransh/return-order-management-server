package me.saransh13.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteProcessingRequest {
    private long requestId;
    private String creditCardNumber;
    private int creditLimit;
    private int processingCharge;
}
