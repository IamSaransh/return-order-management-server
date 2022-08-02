package me.saransh13.apigateway.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MicroserviceDownExceptionTest {
    /**
     * Method under test: {@link MicroserviceDownException#MicroserviceDownException(String)}
     */
    @Test
    void testConstructor() {
        MicroserviceDownException actualMicroserviceDownException = new MicroserviceDownException("Msg");
        assertNull(actualMicroserviceDownException.getCause());
        assertEquals(0, actualMicroserviceDownException.getSuppressed().length);
        assertEquals("Msg", actualMicroserviceDownException.getMessage());
        assertEquals("Msg", actualMicroserviceDownException.getLocalizedMessage());
    }
}

