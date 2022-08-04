package me.saransh13.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InvalidComponentTypeExceptionTest {
    /**
     * Method under test: {@link InvalidComponentTypeException#InvalidComponentTypeException(String)}
     */
    @Test
    void testConstructor() {
        InvalidComponentTypeException actualInvalidComponentTypeException = new InvalidComponentTypeException(
                "An error occurred");
        assertNull(actualInvalidComponentTypeException.getCause());
        assertEquals(0, actualInvalidComponentTypeException.getSuppressed().length);
        assertEquals("An error occurred", actualInvalidComponentTypeException.getMessage());
        assertEquals("An error occurred", actualInvalidComponentTypeException.getLocalizedMessage());
    }
}

