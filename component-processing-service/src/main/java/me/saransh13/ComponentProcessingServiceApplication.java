package me.saransh13;

import me.saransh13.model.ComponentType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ComponentProcessingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentProcessingServiceApplication.class, args);

	}


}
