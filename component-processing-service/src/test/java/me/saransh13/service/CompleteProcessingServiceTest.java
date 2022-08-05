package me.saransh13.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import me.saransh13.domain.Request;
import me.saransh13.exception.PaymentFailedException;
import me.saransh13.model.CompleteProcessingRequest;
import me.saransh13.repository.RequestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompleteProcessingService.class})
@ExtendWith(SpringExtension.class)
class CompleteProcessingServiceTest {
    @Autowired
    private CompleteProcessingService completeProcessingService;

    @MockBean
    private RequestRepository requestRepository;

    /**
     * Method under test: {@link CompleteProcessingService#paymentResponse(CompleteProcessingRequest)}
     */
    @Test
    void testPaymentResponse() throws PaymentFailedException {
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
        Optional<Request> ofResult = Optional.of(request);
        when(requestRepository.findById(anyLong())).thenReturn(ofResult);
        assertEquals("Insufficient funds/ credit Limit for Transaction",
                completeProcessingService.paymentResponse(new CompleteProcessingRequest(123L, "42", 1, 1)).getStatus());
        verify(requestRepository).findById(anyLong());
    }

    /**
     * Method under test: {@link CompleteProcessingService#paymentResponse(CompleteProcessingRequest)}
     */
    @Test
    void testPaymentResponse2() throws PaymentFailedException {
        Request request = mock(Request.class);
        when(request.getPackagingAndDeliveryCharge()).thenReturn(-1);
        when(request.getProcessingCharge()).thenReturn(1);
        doNothing().when(request).setCardLimit((Integer) any());
        doNothing().when(request).setCardNumber((String) any());
        doNothing().when(request).setComponentName((String) any());
        doNothing().when(request).setComponentType((String) any());
        doNothing().when(request).setDateOfDelivery((LocalDate) any());
        doNothing().when(request).setPackagingAndDeliveryCharge(anyInt());
        doNothing().when(request).setPaymentCompleted(anyBoolean());
        doNothing().when(request).setProcessingCharge(anyInt());
        doNothing().when(request).setQuantity(anyInt());
        doNothing().when(request).setRequestId(anyLong());
        doNothing().when(request).setUserContactNumber((String) any());
        doNothing().when(request).setUsername((String) any());
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
        Optional<Request> ofResult = Optional.of(request);

        Request request1 = new Request();
        request1.setCardLimit(1);
        request1.setCardNumber("42");
        request1.setComponentName("Component Name");
        request1.setComponentType("Component Type");
        request1.setDateOfDelivery(LocalDate.ofEpochDay(1L));
        request1.setPackagingAndDeliveryCharge(1);
        request1.setPaymentCompleted(true);
        request1.setProcessingCharge(1);
        request1.setQuantity(1);
        request1.setRequestId(123L);
        request1.setUserContactNumber("42");
        request1.setUsername("janedoe");
        when(requestRepository.save((Request) any())).thenReturn(request1);
        when(requestRepository.findById(anyLong())).thenReturn(ofResult);
        assertEquals("Payment Successful",
                completeProcessingService.paymentResponse(new CompleteProcessingRequest(123L, "42", 1, 1)).getStatus());
        verify(requestRepository).save((Request) any());
        verify(requestRepository).findById(anyLong());
        verify(request).getPackagingAndDeliveryCharge();
        verify(request).getProcessingCharge();
        verify(request, atLeast(1)).setCardLimit((Integer) any());
        verify(request, atLeast(1)).setCardNumber((String) any());
        verify(request).setComponentName((String) any());
        verify(request).setComponentType((String) any());
        verify(request).setDateOfDelivery((LocalDate) any());
        verify(request).setPackagingAndDeliveryCharge(anyInt());
        verify(request, atLeast(1)).setPaymentCompleted(anyBoolean());
        verify(request).setProcessingCharge(anyInt());
        verify(request).setQuantity(anyInt());
        verify(request).setRequestId(anyLong());
        verify(request).setUserContactNumber((String) any());
        verify(request).setUsername((String) any());
    }

    /**
     * Method under test: {@link CompleteProcessingService#paymentResponse(CompleteProcessingRequest)}
     */
    @Test
    void testPaymentResponse3() throws PaymentFailedException {
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
        when(requestRepository.save((Request) any())).thenReturn(request);
        when(requestRepository.findById(anyLong())).thenReturn(Optional.empty());
        Request request1 = mock(Request.class);
        when(request1.getPackagingAndDeliveryCharge()).thenReturn(1);
        when(request1.getProcessingCharge()).thenReturn(1);
        doNothing().when(request1).setCardLimit((Integer) any());
        doNothing().when(request1).setCardNumber((String) any());
        doNothing().when(request1).setComponentName((String) any());
        doNothing().when(request1).setComponentType((String) any());
        doNothing().when(request1).setDateOfDelivery((LocalDate) any());
        doNothing().when(request1).setPackagingAndDeliveryCharge(anyInt());
        doNothing().when(request1).setPaymentCompleted(anyBoolean());
        doNothing().when(request1).setProcessingCharge(anyInt());
        doNothing().when(request1).setQuantity(anyInt());
        doNothing().when(request1).setRequestId(anyLong());
        doNothing().when(request1).setUserContactNumber((String) any());
        doNothing().when(request1).setUsername((String) any());
        request1.setCardLimit(1);
        request1.setCardNumber("42");
        request1.setComponentName("Component Name");
        request1.setComponentType("Component Type");
        request1.setDateOfDelivery(LocalDate.ofEpochDay(1L));
        request1.setPackagingAndDeliveryCharge(1);
        request1.setPaymentCompleted(true);
        request1.setProcessingCharge(1);
        request1.setQuantity(1);
        request1.setRequestId(123L);
        request1.setUserContactNumber("42");
        request1.setUsername("janedoe");
        assertThrows(PaymentFailedException.class,
                () -> completeProcessingService.paymentResponse(new CompleteProcessingRequest(123L, "42", 1, 1)));
        verify(requestRepository).findById(anyLong());
        verify(request1).setCardLimit((Integer) any());
        verify(request1).setCardNumber((String) any());
        verify(request1).setComponentName((String) any());
        verify(request1).setComponentType((String) any());
        verify(request1).setDateOfDelivery((LocalDate) any());
        verify(request1).setPackagingAndDeliveryCharge(anyInt());
        verify(request1).setPaymentCompleted(anyBoolean());
        verify(request1).setProcessingCharge(anyInt());
        verify(request1).setQuantity(anyInt());
        verify(request1).setRequestId(anyLong());
        verify(request1).setUserContactNumber((String) any());
        verify(request1).setUsername((String) any());
    }

    /**
     * Method under test: {@link CompleteProcessingService#paymentResponse(CompleteProcessingRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPaymentResponse4() throws PaymentFailedException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at me.saransh13.service.CompleteProcessingService.paymentResponse(CompleteProcessingService.java:25)
        //   In order to prevent paymentResponse(CompleteProcessingRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   paymentResponse(CompleteProcessingRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        Request request = mock(Request.class);
        when(request.getPackagingAndDeliveryCharge()).thenReturn(1);
        when(request.getProcessingCharge()).thenReturn(1);
        doNothing().when(request).setCardLimit((Integer) any());
        doNothing().when(request).setCardNumber((String) any());
        doNothing().when(request).setComponentName((String) any());
        doNothing().when(request).setComponentType((String) any());
        doNothing().when(request).setDateOfDelivery((LocalDate) any());
        doNothing().when(request).setPackagingAndDeliveryCharge(anyInt());
        doNothing().when(request).setPaymentCompleted(anyBoolean());
        doNothing().when(request).setProcessingCharge(anyInt());
        doNothing().when(request).setQuantity(anyInt());
        doNothing().when(request).setRequestId(anyLong());
        doNothing().when(request).setUserContactNumber((String) any());
        doNothing().when(request).setUsername((String) any());
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
        Optional<Request> ofResult = Optional.of(request);

        Request request1 = new Request();
        request1.setCardLimit(1);
        request1.setCardNumber("42");
        request1.setComponentName("Component Name");
        request1.setComponentType("Component Type");
        request1.setDateOfDelivery(LocalDate.ofEpochDay(1L));
        request1.setPackagingAndDeliveryCharge(1);
        request1.setPaymentCompleted(true);
        request1.setProcessingCharge(1);
        request1.setQuantity(1);
        request1.setRequestId(123L);
        request1.setUserContactNumber("42");
        request1.setUsername("janedoe");
        when(requestRepository.save((Request) any())).thenReturn(request1);
        when(requestRepository.findById(anyLong())).thenReturn(ofResult);
        completeProcessingService.paymentResponse(null);
    }
}

