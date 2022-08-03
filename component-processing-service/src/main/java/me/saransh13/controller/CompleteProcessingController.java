package me.saransh13.controller;

import me.saransh13.exception.PaymentFailedException;
import me.saransh13.model.CompleteProcessingRequest;
import me.saransh13.model.PaymentStatusResponse;
import me.saransh13.service.CompleteProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process/v1")
public class CompleteProcessingController {


    @Autowired
    private CompleteProcessingService completeProcessingService;

    @PostMapping("/completeProcessing")
    public ResponseEntity<PaymentStatusResponse> completeProcessing(
            @RequestParam long requestId,
            @RequestParam String creditCardNumber,
            @RequestParam int creditLimit,
            @RequestParam int processingCharge
    )
            throws PaymentFailedException {
        CompleteProcessingRequest request = CompleteProcessingRequest.builder()
                .requestId(requestId)
                .creditCardNumber(creditCardNumber)
                .processingCharge(processingCharge)
                .creditLimit(creditLimit)
                .build();
        return new ResponseEntity<>(completeProcessingService.paymentResponse(request), HttpStatus.OK);
    }

}
