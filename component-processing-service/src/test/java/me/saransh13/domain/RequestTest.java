package me.saransh13.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class RequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Request#Request()}
     *   <li>{@link Request#setCardLimit(Integer)}
     *   <li>{@link Request#setCardNumber(String)}
     *   <li>{@link Request#setComponentName(String)}
     *   <li>{@link Request#setComponentType(String)}
     *   <li>{@link Request#setDateOfDelivery(LocalDate)}
     *   <li>{@link Request#setPackagingAndDeliveryCharge(int)}
     *   <li>{@link Request#setPaymentCompleted(boolean)}
     *   <li>{@link Request#setProcessingCharge(int)}
     *   <li>{@link Request#setQuantity(int)}
     *   <li>{@link Request#setRequestId(long)}
     *   <li>{@link Request#setUserContactNumber(String)}
     *   <li>{@link Request#setUsername(String)}
     *   <li>{@link Request#getCardLimit()}
     *   <li>{@link Request#getCardNumber()}
     *   <li>{@link Request#getComponentName()}
     *   <li>{@link Request#getComponentType()}
     *   <li>{@link Request#getDateOfDelivery()}
     *   <li>{@link Request#getPackagingAndDeliveryCharge()}
     *   <li>{@link Request#getProcessingCharge()}
     *   <li>{@link Request#getQuantity()}
     *   <li>{@link Request#getRequestId()}
     *   <li>{@link Request#getUserContactNumber()}
     *   <li>{@link Request#getUsername()}
     *   <li>{@link Request#isPaymentCompleted()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Request actualRequest = new Request();
        actualRequest.setCardLimit(1);
        actualRequest.setCardNumber("42");
        actualRequest.setComponentName("Component Name");
        actualRequest.setComponentType("Component Type");
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualRequest.setDateOfDelivery(ofEpochDayResult);
        actualRequest.setPackagingAndDeliveryCharge(1);
        actualRequest.setPaymentCompleted(true);
        actualRequest.setProcessingCharge(1);
        actualRequest.setQuantity(1);
        actualRequest.setRequestId(123L);
        actualRequest.setUserContactNumber("42");
        actualRequest.setUsername("janedoe");
        assertEquals(1, actualRequest.getCardLimit().intValue());
        assertEquals("42", actualRequest.getCardNumber());
        assertEquals("Component Name", actualRequest.getComponentName());
        assertEquals("Component Type", actualRequest.getComponentType());
        assertSame(ofEpochDayResult, actualRequest.getDateOfDelivery());
        assertEquals(1, actualRequest.getPackagingAndDeliveryCharge());
        assertEquals(1, actualRequest.getProcessingCharge());
        assertEquals(1, actualRequest.getQuantity());
        assertEquals(123L, actualRequest.getRequestId());
        assertEquals("42", actualRequest.getUserContactNumber());
        assertEquals("janedoe", actualRequest.getUsername());
        assertTrue(actualRequest.isPaymentCompleted());
    }

    /**
     * Method under test: {@link Request#Request(long, String, String, String, String, int, int, int, LocalDate, String, Integer, boolean)}
     */
    @Test
    void testConstructor2() {
        Request actualRequest = new Request(123L, "janedoe", "42", "Component Type", "Component Name", 1, 1, 1,
                LocalDate.ofEpochDay(1L), "42", 1, true);

        assertEquals(1, actualRequest.getCardLimit().intValue());
        assertTrue(actualRequest.isPaymentCompleted());
        assertEquals("janedoe", actualRequest.getUsername());
        assertEquals("42", actualRequest.getUserContactNumber());
        assertEquals(123L, actualRequest.getRequestId());
        assertEquals(1, actualRequest.getQuantity());
        assertEquals(1, actualRequest.getProcessingCharge());
        assertEquals(1, actualRequest.getPackagingAndDeliveryCharge());
        assertEquals("1970-01-02", actualRequest.getDateOfDelivery().toString());
        assertEquals("Component Type", actualRequest.getComponentType());
        assertEquals("Component Name", actualRequest.getComponentName());
        assertEquals("42", actualRequest.getCardNumber());
    }
}

