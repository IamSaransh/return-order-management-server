package me.saransh13.authorizationserver.constant;

import me.saransh13.authorizationserver.domain.Customer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author saransh
 */
public class SecurityConstant {
    private SecurityConstant(){}
    public static final long EXPIRATION_TIME = (long) 1.8e+6;
    public static final String TOKEN_HEADER_PREFIX  =  "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String FORBIDDEN_MESSAGE = "You are forbidden to access this value";
    public static final String ACCESS_DENIED_MESSAGE = "You are not Accessed to access this value";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot Be Verified";
    public static final String ME_SARANSH13 = "Saransh13.me";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
//    public static final String[] PUBLIC_URLS = {"/login", "/authenticate", "/signup"};
    public static final String[] PUBLIC_URLS = {"**", "/h2-console/**"};

    public static void main(String[] args) {
        Set<Customer> s= new HashSet<>();
        Customer c1 = Customer.builder().email("saransh").password("asdfgg").build();
        Customer c2 = Customer.builder().email("saransh").password("asdfgg").build();
        s.add(c1);
        s.add(c2);
        System.out.println(s.size());
    }
}
