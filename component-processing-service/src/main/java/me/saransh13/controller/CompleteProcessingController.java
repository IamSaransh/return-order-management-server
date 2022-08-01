package me.saransh13.controller;

import me.saransh13.exception.PaymentFailedException;
import me.saransh13.model.CompleteProcessingRequest;
import me.saransh13.model.PaymentStatusResponse;
import me.saransh13.service.CompleteProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/process/v1")
public class CompleteProcessingController {


    @Autowired
    private CompleteProcessingService completeProcessingService;

    @GetMapping("/completeProcessing")
    public ResponseEntity<PaymentStatusResponse> completeProcessing(@RequestBody CompleteProcessingRequest request)
            throws PaymentFailedException {
        return new ResponseEntity<>(completeProcessingService.paymentResponse(request), HttpStatus.OK);
    }

}
