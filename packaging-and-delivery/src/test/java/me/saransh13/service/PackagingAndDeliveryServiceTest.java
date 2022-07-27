package me.saransh13.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import me.saransh13.config.CostConfig;
import me.saransh13.exception.InvalidComponentTypeException;
import me.saransh13.model.PackagingAndDeliveryResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PackagingAndDeliveryService.class, CostConfig.class})
@ExtendWith(SpringExtension.class)
class PackagingAndDeliveryServiceTest {
    @Autowired
    private CostConfig costConfig;

    @Autowired
    private PackagingAndDeliveryService packagingAndDeliveryService;

    /**
     * Method under test: {@link PackagingAndDeliveryService#getPackagingAndDeliveryCharge(String, int)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge() throws InvalidComponentTypeException {
        // Arrange
        // TODO: Populate arranged inputs
        String componentType = "";
        int count = 0;

        // Act
        ResponseEntity<PackagingAndDeliveryResponse> actualPackagingAndDeliveryCharge = this.packagingAndDeliveryService
                .getPackagingAndDeliveryCharge(componentType, count);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link PackagingAndDeliveryService#getPackagingAndDeliveryCharge(String, int)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge2() throws InvalidComponentTypeException {
        assertThrows(InvalidComponentTypeException.class,
                () -> packagingAndDeliveryService.getPackagingAndDeliveryCharge("Component Type", 3));
    }

    /**
     * Method under test: {@link PackagingAndDeliveryService#getPackagingAndDeliveryCharge(String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPackagingAndDeliveryCharge3() throws InvalidComponentTypeException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at me.saransh13.service.PackagingAndDeliveryService.getPackagingAndDeliveryCharge(PackagingAndDeliveryService.java:33)
        //   In order to prevent getPackagingAndDeliveryCharge(String, int)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getPackagingAndDeliveryCharge(String, int).
        //   See https://diff.blue/R013 to resolve this issue.

        packagingAndDeliveryService.getPackagingAndDeliveryCharge(PackagingAndDeliveryService.ACCESSORY, 3);
    }

    /**
     * Method under test: {@link PackagingAndDeliveryService#getPackagingAndDeliveryCharge(String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPackagingAndDeliveryCharge4() throws InvalidComponentTypeException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at me.saransh13.service.PackagingAndDeliveryService.getPackagingAndDeliveryCharge(PackagingAndDeliveryService.java:33)
        //   In order to prevent getPackagingAndDeliveryCharge(String, int)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getPackagingAndDeliveryCharge(String, int).
        //   See https://diff.blue/R013 to resolve this issue.

        packagingAndDeliveryService.getPackagingAndDeliveryCharge(PackagingAndDeliveryService.INTEGRAL_ITEM, 3);
    }
}

