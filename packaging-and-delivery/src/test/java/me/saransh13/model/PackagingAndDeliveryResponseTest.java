package me.saransh13.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PackagingAndDeliveryResponseTest {
    /**
     * Method under test: {@link PackagingAndDeliveryResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new PackagingAndDeliveryResponse(1, 1)).canEqual("Other"));
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(1, 1);
        assertTrue(packagingAndDeliveryResponse.canEqual(new PackagingAndDeliveryResponse()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PackagingAndDeliveryResponse#PackagingAndDeliveryResponse()}
     *   <li>{@link PackagingAndDeliveryResponse#setDeliveryCharge(int)}
     *   <li>{@link PackagingAndDeliveryResponse#setPackagingCharge(int)}
     *   <li>{@link PackagingAndDeliveryResponse#toString()}
     *   <li>{@link PackagingAndDeliveryResponse#getDeliveryCharge()}
     *   <li>{@link PackagingAndDeliveryResponse#getPackagingCharge()}
     * </ul>
     */
    @Test
    void testConstructor() {
        PackagingAndDeliveryResponse actualPackagingAndDeliveryResponse = new PackagingAndDeliveryResponse();
        actualPackagingAndDeliveryResponse.setDeliveryCharge(1);
        actualPackagingAndDeliveryResponse.setPackagingCharge(1);
        String actualToStringResult = actualPackagingAndDeliveryResponse.toString();
        assertEquals(1, actualPackagingAndDeliveryResponse.getDeliveryCharge());
        assertEquals(1, actualPackagingAndDeliveryResponse.getPackagingCharge());
        assertEquals("PackagingAndDeliveryResponse(deliveryCharge=1, packagingCharge=1)", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PackagingAndDeliveryResponse#PackagingAndDeliveryResponse(int, int)}
     *   <li>{@link PackagingAndDeliveryResponse#setDeliveryCharge(int)}
     *   <li>{@link PackagingAndDeliveryResponse#setPackagingCharge(int)}
     *   <li>{@link PackagingAndDeliveryResponse#toString()}
     *   <li>{@link PackagingAndDeliveryResponse#getDeliveryCharge()}
     *   <li>{@link PackagingAndDeliveryResponse#getPackagingCharge()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        PackagingAndDeliveryResponse actualPackagingAndDeliveryResponse = new PackagingAndDeliveryResponse(1, 1);
        actualPackagingAndDeliveryResponse.setDeliveryCharge(1);
        actualPackagingAndDeliveryResponse.setPackagingCharge(1);
        String actualToStringResult = actualPackagingAndDeliveryResponse.toString();
        assertEquals(1, actualPackagingAndDeliveryResponse.getDeliveryCharge());
        assertEquals(1, actualPackagingAndDeliveryResponse.getPackagingCharge());
        assertEquals("PackagingAndDeliveryResponse(deliveryCharge=1, packagingCharge=1)", actualToStringResult);
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#equals(Object)}
     */
    @Test
    void testEquals() {
        assertNotEquals(new PackagingAndDeliveryResponse(1, 1), null);
        assertNotEquals(new PackagingAndDeliveryResponse(1, 1), "Different type to PackagingAndDeliveryResponse");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PackagingAndDeliveryResponse#equals(Object)}
     *   <li>{@link PackagingAndDeliveryResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(1, 1);
        assertEquals(packagingAndDeliveryResponse, packagingAndDeliveryResponse);
        int expectedHashCodeResult = packagingAndDeliveryResponse.hashCode();
        assertEquals(expectedHashCodeResult, packagingAndDeliveryResponse.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PackagingAndDeliveryResponse#equals(Object)}
     *   <li>{@link PackagingAndDeliveryResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(1, 1);
        PackagingAndDeliveryResponse packagingAndDeliveryResponse1 = new PackagingAndDeliveryResponse(1, 1);

        assertEquals(packagingAndDeliveryResponse, packagingAndDeliveryResponse1);
        int expectedHashCodeResult = packagingAndDeliveryResponse.hashCode();
        assertEquals(expectedHashCodeResult, packagingAndDeliveryResponse1.hashCode());
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#equals(Object)}
     */
    @Test
    void testEquals4() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(3, 1);
        assertNotEquals(packagingAndDeliveryResponse, new PackagingAndDeliveryResponse(1, 1));
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#equals(Object)}
     */
    @Test
    void testEquals5() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(1, 3);
        assertNotEquals(packagingAndDeliveryResponse, new PackagingAndDeliveryResponse(1, 1));
    }
}

