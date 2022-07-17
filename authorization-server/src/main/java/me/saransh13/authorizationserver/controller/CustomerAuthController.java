package me.saransh13.authorizationserver.controller;


import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author saransh
 */
@RestController
public class CustomerAuthController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/hello")
    public String helloWorldMethod(){
        return "hello world";
    }

    @GetMapping("/v2/users")
    public List<Customer> retrieveAllUsersv2(){
        return repository.findAll();
    }

    @GetMapping("/v2/users/{email}")
    public Customer retrieveAllUsersv3(@PathVariable String email){
        return repository.findCustomerByEmail(email);
    }

}
