package me.saransh13.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.saransh13.model.PackagingAndDeliveryResponse;
import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import me.saransh13.proxy.PackagingAndDeliveryServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static me.saransh13.constants.Constants.DEFAULT_INTEGRAL_DELIVERY_DURATION;
import static me.saransh13.constants.Constants.DEFAULT_INTEGRAL_PROCESSING_CHARGE;

@Service
public class IntegralItemRepairService extends ProcessDetailService {

    @Autowired
    PackagingAndDeliveryServiceProxy proxy;


    public ResponseEntity<ProcessingResponse> getProcessDetail(ProcessingRequest request) {
        //my request mapper auto validated the incoming data so no need to do the null checks as such
        String componentType = request.getComponentType().toString();
        int componentQuantity = request.getQuantity();
        PackagingAndDeliveryResponse chargeResponse = proxy.getPackagingAndDeliveryCharge(
                componentType,
                componentQuantity);
        //map the incoming record to my response class
        LocalDate expectedDeliveredDate = LocalDate.from(LocalDateTime.now()
                .plusDays(DEFAULT_INTEGRAL_DELIVERY_DURATION));
        long randomRequestId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        return  ResponseEntity.ok(ProcessingResponse.builder()
                .requestId(randomRequestId)
                .processingCharge(DEFAULT_INTEGRAL_PROCESSING_CHARGE)
                .packagingAndDeliveryCharge(chargeResponse.getDeliveryCharge() + chargeResponse.getPackagingCharge())
                .dateOfDelivery(expectedDeliveredDate)
                .build());





    }
}
