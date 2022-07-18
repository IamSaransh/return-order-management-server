package me.saransh13.authorizationserver.controller;


import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.domain.UserPrincipal;
import me.saransh13.authorizationserver.exception.EmailExistException;
import me.saransh13.authorizationserver.exception.ExceptionHandling;
import me.saransh13.authorizationserver.service.CustomerService;
import me.saransh13.authorizationserver.util.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static me.saransh13.authorizationserver.constant.SecurityConstant.JWT_TOKEN_HEADER;


/**
 * @author saransh
 */
@RestController
public class CustomerAuthController extends ExceptionHandling {

    private CustomerService customerService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public CustomerAuthController(CustomerService customerService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @PostMapping("/login")
    public  ResponseEntity<Customer> login(@RequestBody Customer customer) throws EmailExistException {
        authenticate(customer.getEmail(), customer.getPassword()); // thia method will throw if not valid
        Customer loginCustomer = customerService.getCustomerByEmail(customer.getEmail());
        UserPrincipal userPrincipal = new UserPrincipal(loginCustomer);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginCustomer, jwtHeader, HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @PostMapping("/register")
    public  ResponseEntity<Customer> register(@RequestBody Customer customer) throws EmailExistException {
        Customer registeredCustomer = customerService.register(customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getContactNumber(),
                customer.getPassword());
        return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
    }

}
