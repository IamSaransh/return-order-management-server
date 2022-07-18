package me.saransh13.authorizationserver.controller;


import me.saransh13.authorizationserver.exception.ExceptionHandling;
import me.saransh13.authorizationserver.exception.UserNotFoundException;
import me.saransh13.authorizationserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author saransh
 */
@RestController
public class CustomerAuthController extends ExceptionHandling {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/hello")
    public String helloWorldMethod() throws UserNotFoundException {
//        return "hello world";
        throw new UserNotFoundException("This user not found");
    }
}
