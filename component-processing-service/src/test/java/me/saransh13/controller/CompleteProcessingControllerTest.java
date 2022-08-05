package me.saransh13.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import me.saransh13.model.CompleteProcessingRequest;
import me.saransh13.model.PaymentStatusResponse;
import me.saransh13.service.CompleteProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CompleteProcessingController.class})
@ExtendWith(SpringExtension.class)
class CompleteProcessingControllerTest {
    @Autowired
    private CompleteProcessingController completeProcessingController;

    @MockBean
    private CompleteProcessingService completeProcessingService;

    /**
     * Method under test: {@link CompleteProcessingController#completeProcessing(long, String, int, int)}
     */
    @Test
    void testCompleteProcessing() throws Exception {
        when(completeProcessingService.paymentResponse((CompleteProcessingRequest) any()))
                .thenReturn(new PaymentStatusResponse("Status"));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/process/v1/completeProcessing")
                .param("creditCardNumber", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("creditLimit", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult1.param("processingCharge", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("requestId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(completeProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"Status\"}"));
    }
}

