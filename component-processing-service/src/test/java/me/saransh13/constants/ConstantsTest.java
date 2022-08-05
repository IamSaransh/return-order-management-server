package me.saransh13.constants;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ConstantsTest {
    /**
     * Method under test: default or parameterless constructor of {@link Constants}
     */
    @Test
    void testConstructor() {
        assertThrows(IllegalStateException.class, () -> new Constants());
    }
}

