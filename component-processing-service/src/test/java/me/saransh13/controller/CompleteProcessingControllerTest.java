package me.saransh13.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.saransh13.exception.PaymentFailedException;
import me.saransh13.model.CompleteProcessingRequest;
import me.saransh13.model.PaymentStatusResponse;
import me.saransh13.service.CompleteProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CompleteProcessingController.class})
@ExtendWith(SpringExtension.class)
class CompleteProcessingControllerTest {
    @Autowired
    private CompleteProcessingController completeProcessingController;

    @MockBean
    private CompleteProcessingService completeProcessingService;


    @Test
    void testCompleteProcessing() throws Exception, PaymentFailedException {
        when(completeProcessingService.paymentResponse((CompleteProcessingRequest) any()))
                .thenReturn(new PaymentStatusResponse("Status"));

        CompleteProcessingRequest completeProcessingRequest = new CompleteProcessingRequest();
        completeProcessingRequest.setCreditCardNumber("42");
        completeProcessingRequest.setCreditLimit(1);
        completeProcessingRequest.setProcessingCharge(1);
        completeProcessingRequest.setRequestId(123L);
        String content = (new ObjectMapper()).writeValueAsString(completeProcessingRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/process/v1/completeProcessing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(completeProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"Status\"}"));
    }
}

