package me.saransh13.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Request {
    @Id
    private long requestId;
    private String username;//I'll have it as email
    private String userContactNumber; //@Unique
    private String componentType;
    private String componentName;
    private int quantity;
    private int processingCharge;
    private int packagingAndDeliveryCharge;
    private LocalDate dateOfDelivery;
    private String cardNumber;
    private Integer cardLimit;
    private boolean paymentCompleted;
}