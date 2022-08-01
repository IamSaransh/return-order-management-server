package me.saransh13.service;

import me.saransh13.domain.Request;
import me.saransh13.exception.PaymentFailedException;
import me.saransh13.model.CompleteProcessingRequest;
import me.saransh13.model.PaymentStatusResponse;
import me.saransh13.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static me.saransh13.constants.Constants.*;

@Service
@Transactional
public class CompleteProcessingService {


    @Autowired
    private RequestRepository repository;

    public PaymentStatusResponse paymentResponse(CompleteProcessingRequest confirmationRequest) throws PaymentFailedException {
        Optional<Request> requestsById = repository.findById(confirmationRequest.getRequestId());
        if(requestsById.isEmpty())
            throw new PaymentFailedException(PAYMENT_FAILED_EXCEPTION_MESSAGE);
        Request prevEnquiryRequest;
        try{
            prevEnquiryRequest = requestsById.get();
            int totalPaymentAmount = prevEnquiryRequest.getPackagingAndDeliveryCharge() + prevEnquiryRequest.getProcessingCharge();
            //check for insufficient funds
            if(totalPaymentAmount > confirmationRequest.getCreditLimit())
                return new PaymentStatusResponse(INSUFFICIENT_PAYMENT_LIMIT_MESSAGE);
            //if he has sufficient funds we start the transaction
            prevEnquiryRequest.setCardNumber(confirmationRequest.getCreditCardNumber());
            prevEnquiryRequest.setCardLimit(confirmationRequest.getCreditLimit());
            prevEnquiryRequest.setPaymentCompleted(true);
            repository.save(prevEnquiryRequest);
            return new PaymentStatusResponse(SUCCESS_MESSAGE);
        } catch(Exception e)
        {
            throw new PaymentFailedException(PAYMENT_FAILED_EXCEPTION_MESSAGE);
        }
    }
}