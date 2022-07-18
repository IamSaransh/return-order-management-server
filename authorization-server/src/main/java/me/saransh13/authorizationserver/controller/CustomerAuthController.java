package me.saransh13.authorizationserver.controller;


import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.exception.EmailExistException;
import me.saransh13.authorizationserver.exception.ExceptionHandling;
import me.saransh13.authorizationserver.exception.UserNotFoundException;
import me.saransh13.authorizationserver.repository.CustomerRepository;
import me.saransh13.authorizationserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author saransh
 */
@RestController
public class CustomerAuthController extends ExceptionHandling {

    private CustomerService customerService;

    @Autowired
    public CustomerAuthController(CustomerService customerService){
        this.customerService = customerService;
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
