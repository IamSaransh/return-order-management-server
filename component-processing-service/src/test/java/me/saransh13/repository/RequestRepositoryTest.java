package me.saransh13.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import me.saransh13.domain.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {RequestRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"me.saransh13.domain"})
@DataJpaTest
class RequestRepositoryTest {
    @Autowired
    private RequestRepository requestRepository;

    /**
     * Method under test: {@link RequestRepository#findById(long)}
     */
    @Test
    void testFindById() {
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
        requestRepository.save(request);
        requestRepository.save(request1);
        assertFalse(requestRepository.findById(1L).isPresent());
    }
}

