package me.saransh13.authorizationserver.controller;


import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.domain.UserPrincipal;
import me.saransh13.authorizationserver.exception.EmailExistException;
import me.saransh13.authorizationserver.exception.ExceptionHandling;
import me.saransh13.authorizationserver.model.AuthenticateResponse;
import me.saransh13.authorizationserver.model.LoginHttpRequest;
import me.saransh13.authorizationserver.model.SignupHttpRequest;
import me.saransh13.authorizationserver.service.CustomerService;
import me.saransh13.authorizationserver.util.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static me.saransh13.authorizationserver.constant.SecurityConstant.JWT_TOKEN_HEADER;


/**
 * @author saransh
 */
@RestController
@RequestMapping("auth/v1")
public class CustomerAuthController extends ExceptionHandling {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public CustomerAuthController(CustomerService customerService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello World!";
    }

    @PostMapping("/login")
    public  ResponseEntity<Customer> login(@RequestBody LoginHttpRequest loginRequest) {
        authenticate(loginRequest.getEmail(), loginRequest.getPassword()); // this method will throw if not valid
        Customer loginCustomer = customerService.getCustomerByEmail(loginRequest.getEmail());
        UserPrincipal userPrincipal = new UserPrincipal(loginCustomer);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginCustomer, jwtHeader, HttpStatus.OK);
    }

    @PostMapping("/register")
    public  ResponseEntity<Customer> register(@RequestBody SignupHttpRequest signupRequest) throws EmailExistException {
        Customer registeredCustomer = customerService.register(signupRequest.getEmail(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getContactNumber(),
                signupRequest.getPassword());
        return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public  ResponseEntity<AuthenticateResponse> isAuthenticated () {
         return new ResponseEntity<>(customerService.authenticateCustomer(), HttpStatus.OK);
    }


    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

}
