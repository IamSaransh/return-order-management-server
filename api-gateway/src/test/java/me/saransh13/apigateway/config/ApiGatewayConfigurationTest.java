package me.saransh13.apigateway.config;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import me.saransh13.apigateway.filter.AuthenticationFilter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApiGatewayConfiguration.class, RouteLocatorBuilder.class,
        PathRoutePredicateFactory.class})
@ExtendWith(SpringExtension.class)
class ApiGatewayConfigurationTest {
    @Autowired
    private ApiGatewayConfiguration apiGatewayConfiguration;

    @MockBean
    private AuthenticationFilter authenticationFilter;

    /**
     * Method under test: {@link ApiGatewayConfiguration#gatewayRouter(RouteLocatorBuilder)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGatewayRouter() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gatewayRouter(RouteLocatorBuilder)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "obj" is null
        //   In order to prevent gatewayRouter(RouteLocatorBuilder)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   gatewayRouter(RouteLocatorBuilder).
        //   See https://diff.blue/R013 to resolve this issue.

        ApiGatewayConfiguration apiGatewayConfiguration = new ApiGatewayConfiguration(new AuthenticationFilter(null));
        apiGatewayConfiguration.gatewayRouter(new RouteLocatorBuilder(new AnnotationConfigReactiveWebApplicationContext()))
                .getRoutes();
    }

    /**
     * Method under test: {@link ApiGatewayConfiguration#gatewayRouter(RouteLocatorBuilder)}
     */
    @Test
    void testGatewayRouter2() throws BeansException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gatewayRouter(RouteLocatorBuilder)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        ApiGatewayConfiguration apiGatewayConfiguration = new ApiGatewayConfiguration(new AuthenticationFilter(null));
        AnnotationConfigApplicationContext annotationConfigApplicationContext = mock(
                AnnotationConfigApplicationContext.class);
        when(annotationConfigApplicationContext.getBean((Class<PathRoutePredicateFactory>) any()))
                .thenReturn(new PathRoutePredicateFactory());
        apiGatewayConfiguration.gatewayRouter(new RouteLocatorBuilder(annotationConfigApplicationContext)).getRoutes();
        verify(annotationConfigApplicationContext).getBean((Class<PathRoutePredicateFactory>) any());
    }

    /**
     * Method under test: {@link ApiGatewayConfiguration#httpClient()}
     */
    @Test
    void testHttpClient() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by httpClient()
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        apiGatewayConfiguration.httpClient();
    }
}

