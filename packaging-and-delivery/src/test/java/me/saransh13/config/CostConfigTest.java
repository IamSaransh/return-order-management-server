package me.saransh13.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {CostConfig.class})
@ExtendWith(SpringExtension.class)
class CostConfigTest {
    @Autowired
    private CostConfig costConfig;


    @Test
    void testCanEqual() {
        assertFalse((new CostConfig()).canEqual("Other"));
    }



    @Test
    void testConstructor() {
        CostConfig actualCostConfig = new CostConfig();
        HashMap<String, Integer> stringIntegerMap = new HashMap<>();
        actualCostConfig.setDeliveryItem(stringIntegerMap);
        HashMap<String, Integer> stringIntegerMap1 = new HashMap<>();
        actualCostConfig.setPackagingItem(stringIntegerMap1);
        String actualToStringResult = actualCostConfig.toString();
        assertSame(stringIntegerMap, actualCostConfig.getDeliveryItem());
        assertSame(stringIntegerMap1, actualCostConfig.getPackagingItem());
        assertEquals("CostConfig(deliveryItem={}, packagingItem={})", actualToStringResult);
    }



    @Test
    void testEquals() {
        HashMap<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("Key", 42);

        CostConfig costConfig = new CostConfig();
        costConfig.setDeliveryItem(stringIntegerMap);
        costConfig.setPackagingItem(new HashMap<>());

        CostConfig costConfig1 = new CostConfig();
        costConfig1.setDeliveryItem(new HashMap<>());
        costConfig1.setPackagingItem(new HashMap<>());
        assertNotEquals(costConfig, costConfig1);
    }


}

