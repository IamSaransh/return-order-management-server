package me.saransh13.controller;

import me.saransh13.exception.InvalidComponentTypeException;
import me.saransh13.service.PackagingAndDeliveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PackagingAndDeliveryController.class})
@ExtendWith(SpringExtension.class)
class PackagingAndDeliveryControllerTest {
    @Autowired
    private PackagingAndDeliveryController packagingAndDeliveryController;

    @MockBean
    private PackagingAndDeliveryService packagingAndDeliveryService;

    /**
     * Method under test: {@link PackagingAndDeliveryController#getPackagingAndDeliveryCharge(String, Integer)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge() throws Exception {
        when(packagingAndDeliveryService.getPackagingAndDeliveryCharge((String) any(), anyInt()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/getPackagingAndDeliveryCharge")
                .param("componentType", "foo").param("count", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(packagingAndDeliveryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link PackagingAndDeliveryController#getPackagingAndDeliveryCharge(String, Integer)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge2() throws Exception {
        when(packagingAndDeliveryService.getPackagingAndDeliveryCharge((String) any(), anyInt()))
                .thenThrow(new InvalidComponentTypeException("An error occurred"));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/v1/getPackagingAndDeliveryCharge")
                .param("componentType", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("count", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(packagingAndDeliveryController)
                .build()
                .perform(requestBuilder);
        System.out.println(actualPerformResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

