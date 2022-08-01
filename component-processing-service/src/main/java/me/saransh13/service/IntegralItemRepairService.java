package me.saransh13.service;


import lombok.extern.slf4j.Slf4j;
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

import static me.saransh13.constants.Constants.DEFAULT_INTEGRAL_DELIVERY_DURATION;
import static me.saransh13.constants.Constants.DEFAULT_INTEGRAL_PROCESSING_CHARGE;

@Service
@Qualifier("integralItem-repair-service")
@Slf4j
public class IntegralItemRepairService extends ProcessDetailService {

    @Autowired
    PackagingAndDeliveryServiceProxy proxy;
    @Autowired
    private RequestRepository repository;


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
        int packagingAndDeliveryCharge = chargeResponse.getDeliveryCharge() + chargeResponse.getPackagingCharge();

        //SaveDetails in the database for the further processing
        persistRequestInDatabase(request,
                componentType,
                componentQuantity,
                expectedDeliveredDate,
                randomRequestId,
                packagingAndDeliveryCharge)
        ;
        log.info("returning response");
        log.info(ProcessingResponse.builder()
                .requestId(randomRequestId)
                .processingCharge(DEFAULT_INTEGRAL_PROCESSING_CHARGE)
                .packagingAndDeliveryCharge(packagingAndDeliveryCharge)
                .dateOfDelivery(expectedDeliveredDate)
                .build().toString());

        return ResponseEntity.ok(ProcessingResponse.builder()
                .requestId(randomRequestId)
                .processingCharge(DEFAULT_INTEGRAL_PROCESSING_CHARGE)
                .packagingAndDeliveryCharge(packagingAndDeliveryCharge)
                .dateOfDelivery(expectedDeliveredDate)
                .build());


    }

    private void persistRequestInDatabase(ProcessingRequest request, String componentType, int componentQuantity, LocalDate expectedDeliveredDate, long randomRequestId, int packagingAndDeliveryCharge) {
        Request requestDomainEntity = Request.builder()
                .requestId(randomRequestId)
                .username(request.getUsername())
                .userContactNumber(request.getUserContactNumber())
                .componentType(request.getComponentType().toString())
                .componentName(request.getComponentName())
                .quantity(componentQuantity)
                .processingCharge(DEFAULT_INTEGRAL_PROCESSING_CHARGE)
                .packagingAndDeliveryCharge(packagingAndDeliveryCharge)
                .dateOfDelivery(expectedDeliveredDate)
                .cardNumber(null)
                .cardLimit(null)
                .paymentCompleted(false)
                .build();

        repository.saveAndFlush(requestDomainEntity);
    }
}
