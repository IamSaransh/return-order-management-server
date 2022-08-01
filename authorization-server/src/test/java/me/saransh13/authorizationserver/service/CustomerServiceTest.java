package me.saransh13.authorizationserver.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import me.saransh13.authorizationserver.domain.Customer;
import me.saransh13.authorizationserver.exception.EmailExistException;
import me.saransh13.authorizationserver.repository.CustomerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertTrue(customerService.loadUserByUsername("janedoe").isEnabled());
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        Customer customer = mock(Customer.class);
        when(customer.getPassword()).thenReturn("iloveyou");
        doNothing().when(customer).setContactNumber((String) any());
        doNothing().when(customer).setEmail((String) any());
        doNothing().when(customer).setEnabled(anyBoolean());
        doNothing().when(customer).setFirstName((String) any());
        doNothing().when(customer).setLastName((String) any());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setPfpUrl((String) any());
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertFalse(customerService.loadUserByUsername("janedoe").isEnabled());
        verify(customerRepository).findCustomerByEmail((String) any());
        verify(customer).getPassword();
        verify(customer).setContactNumber((String) any());
        verify(customer).setEmail((String) any());
        verify(customer).setEnabled(anyBoolean());
        verify(customer).setFirstName((String) any());
        verify(customer).setLastName((String) any());
        verify(customer).setPassword((String) any());
        verify(customer).setPfpUrl((String) any());
    }

    /**
     * Method under test: {@link CustomerService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertTrue(customerService.loadUserByUsername("janedoe").isEnabled());
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        Customer customer = mock(Customer.class);
        when(customer.getPassword()).thenReturn("iloveyou");
        doNothing().when(customer).setContactNumber((String) any());
        doNothing().when(customer).setEmail((String) any());
        doNothing().when(customer).setEnabled(anyBoolean());
        doNothing().when(customer).setFirstName((String) any());
        doNothing().when(customer).setLastName((String) any());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setPfpUrl((String) any());
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertFalse(customerService.loadUserByUsername("janedoe").isEnabled());
        verify(customerRepository).findCustomerByEmail((String) any());
        verify(customer).getPassword();
        verify(customer).setContactNumber((String) any());
        verify(customer).setEmail((String) any());
        verify(customer).setEnabled(anyBoolean());
        verify(customer).setFirstName((String) any());
        verify(customer).setLastName((String) any());
        verify(customer).setPassword((String) any());
        verify(customer).setPfpUrl((String) any());
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    void testRegister() throws EmailExistException {
        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertThrows(EmailExistException.class,
                () -> customerService.register("jane.doe@example.org", "Jane", "Doe", "42", "iloveyou"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister2() throws EmailExistException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: email is marked non-null but is null
        //       at me.saransh13.authorizationserver.domain.Customer$CustomerBuilder.email(Customer.java:17)
        //       at me.saransh13.authorizationserver.service.CustomerService.register(CustomerService.java:62)
        //   In order to prevent register(String, String, String, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   register(String, String, String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        customerService.register(null, "Jane", "Doe", "42", "iloveyou");
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister3() throws EmailExistException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at me.saransh13.authorizationserver.service.CustomerService.generateRandomPfpUrl(CustomerService.java:73)
        //       at me.saransh13.authorizationserver.service.CustomerService.register(CustomerService.java:64)
        //   In order to prevent register(String, String, String, String, String)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   register(String, String, String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        customerService.register("", "Jane", "Doe", "42", "iloveyou");
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    void testRegister4() throws EmailExistException {
        when(customerRepository.findCustomerByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class,
                () -> customerService.register("jane.doe@example.org", "Jane", "Doe", "42", "iloveyou"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    void testRegister5() throws EmailExistException {
        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertThrows(EmailExistException.class,
                () -> customerService.register("jane.doe@example.org", "Jane", "Doe", "42", "iloveyou"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister6() throws EmailExistException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: email is marked non-null but is null
        //       at me.saransh13.authorizationserver.domain.Customer$CustomerBuilder.email(Customer.java:17)
        //       at me.saransh13.authorizationserver.service.CustomerService.register(CustomerService.java:62)
        //   In order to prevent register(String, String, String, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   register(String, String, String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        customerService.register(null, "Jane", "Doe", "42", "iloveyou");
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister7() throws EmailExistException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at me.saransh13.authorizationserver.service.CustomerService.generateRandomPfpUrl(CustomerService.java:73)
        //       at me.saransh13.authorizationserver.service.CustomerService.register(CustomerService.java:64)
        //   In order to prevent register(String, String, String, String, String)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   register(String, String, String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        customerService.register("", "Jane", "Doe", "42", "iloveyou");
    }

    /**
     * Method under test: {@link CustomerService#register(String, String, String, String, String)}
     */
    @Test
    void testRegister8() throws EmailExistException {
        when(customerRepository.findCustomerByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class,
                () -> customerService.register("jane.doe@example.org", "Jane", "Doe", "42", "iloveyou"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail() {
        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertSame(customer, customerService.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail2() {
        when(customerRepository.findCustomerByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> customerService.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail3() {
        Customer customer = new Customer();
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setEnabled(true);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPfpUrl("https://example.org/example");
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertSame(customer, customerService.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail4() {
        when(customerRepository.findCustomerByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> customerService.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerService#authenticateCustomer()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticateCustomer() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at me.saransh13.authorizationserver.service.CustomerService.authenticateCustomer(CustomerService.java:94)
        //   In order to prevent authenticateCustomer()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   authenticateCustomer().
        //   See https://diff.blue/R013 to resolve this issue.

        customerService.authenticateCustomer();
    }

    /**
     * Method under test: {@link CustomerService#authenticateCustomer()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticateCustomer2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at me.saransh13.authorizationserver.service.CustomerService.authenticateCustomer(CustomerService.java:94)
        //   In order to prevent authenticateCustomer()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   authenticateCustomer().
        //   See https://diff.blue/R013 to resolve this issue.

        customerService.authenticateCustomer();
    }
}

