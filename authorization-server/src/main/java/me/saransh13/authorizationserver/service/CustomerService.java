package me.saransh13.authorizationserver.service;

import lombok.extern.slf4j.Slf4j;
import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.domain.UserPrincipal;
import me.saransh13.authorizationserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author saransh
 */
@Slf4j
@Service
@Transactional
@Qualifier("userDetailsService")
public class CustomerService implements UserDetailsService {

    CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer =  repository.findCustomerByEmail(username);
        if(customer == null){
            log.error("User not found by username {}",  username);
            throw new UsernameNotFoundException("User not found by username" +  username);
        }
        else{
            UserPrincipal userPrincipal = new UserPrincipal(customer);
            log.info("Returning found user by username {}" , username);
            return userPrincipal;
        }

    }
}
