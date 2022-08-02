package me.saransh13.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PaymentFailedExceptionTest {
    /**
     * Method under test: {@link PaymentFailedException#PaymentFailedException(String)}
     */
    @Test
    void testConstructor() {
        PaymentFailedException actualPaymentFailedException = new PaymentFailedException("An error occurred");
        assertNull(actualPaymentFailedException.getCause());
        assertEquals(0, actualPaymentFailedException.getSuppressed().length);
        assertEquals("An error occurred", actualPaymentFailedException.getMessage());
        assertEquals("An error occurred", actualPaymentFailedException.getLocalizedMessage());
    }
}

