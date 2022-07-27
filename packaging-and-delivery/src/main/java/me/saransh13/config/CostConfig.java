package me.saransh13.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Data
@ConfigurationProperties("packaging-and-delivery")
public class CostConfig {
    private Map<String, Integer> deliveryItem;
    private Map<String, Integer>  packagingItem;
}
