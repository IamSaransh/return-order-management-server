package me.saransh13.service;

import me.saransh13.domain.Request;
import me.saransh13.model.PackagingAndDeliveryResponse;
import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import me.saransh13.proxy.PackagingAndDeliveryServiceProxy;
import me.saransh13.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static me.saransh13.constants.Constants.DEFAULT_ACCESSORY_DELIVERY_DURATION;
import static me.saransh13.constants.Constants.DEFAULT_ACCESSORY_PROCESSING_CHARGE;

@Service
@Qualifier("accessory-replacement-service")
public class AccessoryReplacementService extends ProcessDetailService {
    @Autowired
    PackagingAndDeliveryServiceProxy proxy;
    @Autowired
    private RequestRepository repository;


    @Override
    public ResponseEntity<ProcessingResponse> getProcessDetail(ProcessingRequest request) {
        //my request mapper auto validated the incoming data so no need to do the null checks as such
        String componentType = request.getComponentType().toString();
        int componentQuantity = request.getQuantity();
        //TODO: to throw an invalidComponentException here
        PackagingAndDeliveryResponse chargeResponse = proxy.getPackagingAndDeliveryCharge(
                componentType,
                componentQuantity);
        //map the incoming record to my response class
        LocalDate expectedDeliveredDate = LocalDate.from(LocalDateTime.now()
                .plusDays(DEFAULT_ACCESSORY_DELIVERY_DURATION));
        long randomRequestId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        int packagingAndDeliveryCharge = chargeResponse.getDeliveryCharge() + chargeResponse.getPackagingCharge();

        //SaveDetails in the database for the further processing
        persistRequestInDatabase(request,
                componentType,
                componentQuantity,
                expectedDeliveredDate,
                randomRequestId,
                packagingAndDeliveryCharge)
        ;


        return ResponseEntity.ok(ProcessingResponse.builder()
                .requestId(randomRequestId)
                .processingCharge(DEFAULT_ACCESSORY_PROCESSING_CHARGE)
                .packagingAndDeliveryCharge(packagingAndDeliveryCharge)
                .dateOfDelivery(expectedDeliveredDate)
                .build());


    }

    private void persistRequestInDatabase(ProcessingRequest request, String componentType, int componentQuantity, LocalDate expectedDeliveredDate, long randomRequestId, int packagingAndDeliveryCharge) {
        Request requestDomainEntity = Request.builder()
                .requestId(randomRequestId)
                .username(request.getUsername())
                .userContactNumber(request.getUserContactNumber())
                .componentName(componentType)
                .componentName(request.getComponentName())
                .quantity(componentQuantity)
                .processingCharge(DEFAULT_ACCESSORY_PROCESSING_CHARGE)
                .packagingAndDeliveryCharge(packagingAndDeliveryCharge)
                .dateOfDelivery(expectedDeliveredDate)
                .cardNumber(null)
                .cardLimit(null)
                .paymentCompleted(false)
                .build();

        repository.saveAndFlush(requestDomainEntity);
    }

}