package me.saransh13.apigateway.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AuthenticationTokenNotFoundExceptionTest {
    /**
     * Method under test: {@link AuthenticationTokenNotFoundException#AuthenticationTokenNotFoundException(String)}
     */
    @Test
    void testConstructor() {
        AuthenticationTokenNotFoundException actualAuthenticationTokenNotFoundException = new AuthenticationTokenNotFoundException(
                "Msg");
        assertNull(actualAuthenticationTokenNotFoundException.getCause());
        assertEquals(0, actualAuthenticationTokenNotFoundException.getSuppressed().length);
        assertEquals("Msg", actualAuthenticationTokenNotFoundException.getMessage());
        assertEquals("Msg", actualAuthenticationTokenNotFoundException.getLocalizedMessage());
    }
}

