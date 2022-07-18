package me.saransh13.authorizationserver.util;

import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author saransh
 */
@Component
public class AuthenticationSuccessListener {

    private final LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) throws ExecutionException {
        Object principal  = event.getAuthentication().getPrincipal();
        if(principal instanceof Customer){
            Customer customer = (Customer) event.getAuthentication().getPrincipal();
            loginAttemptService.addUserToLoginAttemptCache(customer.getEmail());
        }
    }
}
