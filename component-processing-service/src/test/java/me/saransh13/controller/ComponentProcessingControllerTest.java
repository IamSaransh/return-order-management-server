package me.saransh13.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.saransh13.model.ComponentType;
import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import me.saransh13.service.AccessoryReplacementService;
import me.saransh13.service.IntegralItemRepairService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ComponentProcessingController.class})
@ExtendWith(SpringExtension.class)
class ComponentProcessingControllerTest {
    @MockBean
    private AccessoryReplacementService accessoryReplacementService;

    @Autowired
    private ComponentProcessingController componentProcessingController;

    @MockBean
    private IntegralItemRepairService integralItemRepairService;

    /**
     * Method under test: {@link ComponentProcessingController#getProcessDetail(ProcessingRequest)}
     */
    @Test
    void testGetProcessDetail() throws Exception {
        when(integralItemRepairService.getProcessDetail((ProcessingRequest) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setComponentName("Component Name");
        processingRequest.setComponentType(ComponentType.INTEGRAL_ITEM);
        processingRequest.setQuantity(1);
        processingRequest.setUserContactNumber("42");
        processingRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(processingRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/process/v1/processDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(componentProcessingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link ComponentProcessingController#getProcessDetail(ProcessingRequest)}
     */
    @Test
    void testGetProcessDetail2() throws Exception {
        when(integralItemRepairService.getProcessDetail((ProcessingRequest) any()))
                .thenReturn(new ResponseEntity<>(new ProcessingResponse(), HttpStatus.CONTINUE));

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setComponentName("Component Name");
        processingRequest.setComponentType(ComponentType.INTEGRAL_ITEM);
        processingRequest.setQuantity(1);
        processingRequest.setUserContactNumber("42");
        processingRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(processingRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/process/v1/processDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(componentProcessingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"requestId\":0,\"processingCharge\":0,\"packagingAndDeliveryCharge\":0,\"dateOfDelivery\":null}"));
    }

    /**
     * Method under test: {@link ComponentProcessingController#getProcessDetail(ProcessingRequest)}
     */
    @Test
    void testGetProcessDetail3() throws Exception {
        when(integralItemRepairService.getProcessDetail((ProcessingRequest) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(accessoryReplacementService.getProcessDetail((ProcessingRequest) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setComponentName("Component Name");
        processingRequest.setComponentType(ComponentType.ACCESSORY);
        processingRequest.setQuantity(1);
        processingRequest.setUserContactNumber("42");
        processingRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(processingRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/process/v1/processDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(componentProcessingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

