package me.saransh13.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import me.saransh13.domain.Request;
import me.saransh13.model.ComponentType;
import me.saransh13.model.PackagingAndDeliveryResponse;
import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import me.saransh13.proxy.PackagingAndDeliveryServiceProxy;
import me.saransh13.repository.RequestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccessoryReplacementService.class})
@ExtendWith(SpringExtension.class)
class AccessoryReplacementServiceTest {
    @Autowired
    private AccessoryReplacementService accessoryReplacementService;

    @MockBean
    private PackagingAndDeliveryServiceProxy packagingAndDeliveryServiceProxy;

    @MockBean
    private RequestRepository requestRepository;

    /**
     * Method under test: {@link AccessoryReplacementService#getProcessDetail(ProcessingRequest)}
     */
    @Test
    void testGetProcessDetail() {
        when(packagingAndDeliveryServiceProxy.getPackagingAndDeliveryCharge((String) any(), anyInt()))
                .thenReturn(new PackagingAndDeliveryResponse(1, 1));

        Request request = new Request();
        request.setCardLimit(1);
        request.setCardNumber("42");
        request.setComponentName("Component Name");
        request.setComponentType("Component Type");
        request.setDateOfDelivery(LocalDate.ofEpochDay(1L));
        request.setPackagingAndDeliveryCharge(1);
        request.setPaymentCompleted(true);
        request.setProcessingCharge(1);
        request.setQuantity(1);
        request.setRequestId(123L);
        request.setUserContactNumber("42");
        request.setUsername("janedoe");
        when(requestRepository.saveAndFlush((Request) any())).thenReturn(request);

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setComponentName("Component Name");
        processingRequest.setComponentType(ComponentType.INTEGRAL_ITEM);
        processingRequest.setQuantity(1);
        processingRequest.setUserContactNumber("42");
        processingRequest.setUsername("janedoe");
        ResponseEntity<ProcessingResponse> actualProcessDetail = accessoryReplacementService
                .getProcessDetail(processingRequest);
        assertTrue(actualProcessDetail.hasBody());
        assertTrue(actualProcessDetail.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualProcessDetail.getStatusCode());
        ProcessingResponse body = actualProcessDetail.getBody();
        assertEquals(2, body.getPackagingAndDeliveryCharge());
        assertEquals(300, body.getProcessingCharge());
        verify(packagingAndDeliveryServiceProxy).getPackagingAndDeliveryCharge((String) any(), anyInt());
        verify(requestRepository).saveAndFlush((Request) any());
    }

    /**
     * Method under test: {@link AccessoryReplacementService#getProcessDetail(ProcessingRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProcessDetail2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at me.saransh13.service.AccessoryReplacementService.getProcessDetail(AccessoryReplacementService.java:43)
        //   In order to prevent getProcessDetail(ProcessingRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getProcessDetail(ProcessingRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        when(packagingAndDeliveryServiceProxy.getPackagingAndDeliveryCharge((String) any(), anyInt())).thenReturn(null);

        Request request = new Request();
        request.setCardLimit(1);
        request.setCardNumber("42");
        request.setComponentName("Component Name");
        request.setComponentType("Component Type");
        request.setDateOfDelivery(LocalDate.ofEpochDay(1L));
        request.setPackagingAndDeliveryCharge(1);
        request.setPaymentCompleted(true);
        request.setProcessingCharge(1);
        request.setQuantity(1);
        request.setRequestId(123L);
        request.setUserContactNumber("42");
        request.setUsername("janedoe");
        when(requestRepository.saveAndFlush((Request) any())).thenReturn(request);

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setComponentName("Component Name");
        processingRequest.setComponentType(ComponentType.INTEGRAL_ITEM);
        processingRequest.setQuantity(1);
        processingRequest.setUserContactNumber("42");
        processingRequest.setUsername("janedoe");
        accessoryReplacementService.getProcessDetail(processingRequest);
    }
}

