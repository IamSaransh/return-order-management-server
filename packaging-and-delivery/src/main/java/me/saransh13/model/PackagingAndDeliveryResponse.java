package me.saransh13.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackagingAndDeliveryResponse {
private int deliveryCharge;
private int packagingCharge;
}