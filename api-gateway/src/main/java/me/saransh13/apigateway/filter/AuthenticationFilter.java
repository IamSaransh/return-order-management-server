package me.saransh13.apigateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.saransh13.apigateway.exception.AuthenticationTokenNotFoundException;
import me.saransh13.apigateway.exception.BadTokenException;
import me.saransh13.apigateway.model.CustomerResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter implements GatewayFilter {
    private static final String CUSTOMER_EMAIL_ID_HEADER = "customer-email-id";
    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("we received a request");
        log.warn("we received same request");
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new AuthenticationTokenNotFoundException("Authorization token not found");
        }

        String authorizationHeader =
                Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

        return webClientBuilder
                .build()
                .get()
                .uri("lb://authorization/authorization/api/authenticate")
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(new BadTokenException("Bad token")))
                .bodyToMono(CustomerResponse.class)
                .map(customerResponse -> {
                    exchange
                            .getRequest()
                            .mutate()
                                    .header(CUSTOMER_EMAIL_ID_HEADER, customerResponse.getEmail());
                            return exchange;
                        }
                ).flatMap(chain::filter);
    }
}