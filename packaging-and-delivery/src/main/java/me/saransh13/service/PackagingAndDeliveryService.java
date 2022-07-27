package me.saransh13.service;

import lombok.extern.slf4j.Slf4j;
import me.saransh13.config.CostConfig;
import me.saransh13.exception.InvalidComponentTypeException;
import me.saransh13.model.PackagingAndDeliveryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PackagingAndDeliveryService {

    private final CostConfig costConfig;
    @Autowired
    public PackagingAndDeliveryService(CostConfig costConfig) {
        this.costConfig = costConfig;
    }

    public static final String ACCESSORY = "accessory";
    public static final String INTEGRAL_ITEM = "integral-item";
    public static final String PROTECTIVE_SHEATH = "protective-sheath";



    public ResponseEntity<PackagingAndDeliveryResponse> getPackagingAndDeliveryCharge(String componentType, int count) throws InvalidComponentTypeException {
       if(!componentType.equalsIgnoreCase(ACCESSORY) && ! componentType.equalsIgnoreCase(INTEGRAL_ITEM))
           throw new InvalidComponentTypeException("Please Send a valid Component Type");
       else{
           int packagingCharge = costConfig.getPackagingItem().get(componentType) * count
                   + costConfig.getPackagingItem().get(PROTECTIVE_SHEATH) * count;
           int deliveryCharge = costConfig.getDeliveryItem().get(componentType) * count ;
           PackagingAndDeliveryResponse response = new PackagingAndDeliveryResponse(deliveryCharge, packagingCharge);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }

    }
}