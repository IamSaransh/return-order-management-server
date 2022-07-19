package me.saransh13.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author saransh
 */
@Configuration
public class ApigatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                .path("/auth/**")
                .filters(gatewayFilterSpec -> gatewayFilterSpec
                        .setStatus(HttpStatus.UNAUTHORIZED)
                )
                .uri("lb://authorization-service")
                        //http://localhost:8765/authorization-service/auth/v1/login
        ).build();
    }
}
