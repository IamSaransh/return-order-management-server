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
    void testCanEqual2() {
        assertFalse((new PackagingAndDeliveryResponse(1, 1)).canEqual("Other"));
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
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
     *   <li>{@link PackagingAndDeliveryResponse#getDeliveryCharge()}
     *   <li>{@link PackagingAndDeliveryResponse#getPackagingCharge()}
     *   <li>{@link PackagingAndDeliveryResponse#toString()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        // TODO: Populate arranged inputs
        PackagingAndDeliveryResponse actualPackagingAndDeliveryResponse = new PackagingAndDeliveryResponse();
        int deliveryCharge = 0;
        actualPackagingAndDeliveryResponse.setDeliveryCharge(deliveryCharge);
        int packagingCharge = 0;
        actualPackagingAndDeliveryResponse.setPackagingCharge(packagingCharge);
        int actualDeliveryCharge = actualPackagingAndDeliveryResponse.getDeliveryCharge();
        int actualPackagingCharge = actualPackagingAndDeliveryResponse.getPackagingCharge();
        String actualToStringResult = actualPackagingAndDeliveryResponse.toString();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PackagingAndDeliveryResponse#PackagingAndDeliveryResponse(int, int)}
     *   <li>{@link PackagingAndDeliveryResponse#setDeliveryCharge(int)}
     *   <li>{@link PackagingAndDeliveryResponse#setPackagingCharge(int)}
     *   <li>{@link PackagingAndDeliveryResponse#getDeliveryCharge()}
     *   <li>{@link PackagingAndDeliveryResponse#getPackagingCharge()}
     *   <li>{@link PackagingAndDeliveryResponse#toString()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange
        // TODO: Populate arranged inputs
        int deliveryCharge = 0;
        int packagingCharge = 0;

        // Act
        PackagingAndDeliveryResponse actualPackagingAndDeliveryResponse = new PackagingAndDeliveryResponse(deliveryCharge,
                packagingCharge);
        int deliveryCharge1 = 0;
        actualPackagingAndDeliveryResponse.setDeliveryCharge(deliveryCharge1);
        int packagingCharge1 = 0;
        actualPackagingAndDeliveryResponse.setPackagingCharge(packagingCharge1);
        int actualDeliveryCharge = actualPackagingAndDeliveryResponse.getDeliveryCharge();
        int actualPackagingCharge = actualPackagingAndDeliveryResponse.getPackagingCharge();
        String actualToStringResult = actualPackagingAndDeliveryResponse.toString();

        // Assert
        // TODO: Add assertions on result
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
    void testConstructor3() {
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
    void testConstructor4() {
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
    void testEquals6() {
        assertNotEquals(new PackagingAndDeliveryResponse(1, 1), null);
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#equals(Object)}
     */
    @Test
    void testEquals7() {
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
    void testEquals8() {
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
    void testEquals9() {
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
    void testEquals10() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(3, 1);
        assertNotEquals(packagingAndDeliveryResponse, new PackagingAndDeliveryResponse(1, 1));
    }

    /**
     * Method under test: {@link PackagingAndDeliveryResponse#equals(Object)}
     */
    @Test
    void testEquals11() {
        PackagingAndDeliveryResponse packagingAndDeliveryResponse = new PackagingAndDeliveryResponse(1, 3);
        assertNotEquals(packagingAndDeliveryResponse, new PackagingAndDeliveryResponse(1, 1));
    }
}

