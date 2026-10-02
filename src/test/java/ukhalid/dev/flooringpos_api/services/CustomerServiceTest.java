package ukhalid.dev.flooringpos_api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void getAllCustomers_shouldReturnAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Smith");

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");

        when(customerRepository.findAll())
                .thenReturn(List.of(customer1, customer2));

        Iterable<Customer> result = customerService.getAllCustomers();

        assertEquals(List.of(customer1, customer2), result);

        verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById_shouldReturnCustomer_whenCustomerExists() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");

        when(customerRepository.findById(1))
                .thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1);

        assertSame(customer, result);

        verify(customerRepository).findById(1);
    }

    @Test
    void getCustomerById_shouldReturnNull_whenCustomerDoesNotExist() {
        when(customerRepository.findById(1))
                .thenReturn(Optional.empty());

        Customer result = customerService.getCustomerById(1);

        assertNull(result);

        verify(customerRepository).findById(1);
    }

    @Test
    void createCustomer_shouldSaveAndReturnCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("01234567890");

        when(customerRepository.save(customer))
                .thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertSame(customer, result);

        verify(customerRepository).save(customer);
    }

    @Test
    void updateCustomer_shouldUpdateAndReturnCustomer_whenCustomerExists() {
        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Smith");
        existingCustomer.setPhone("11111111111");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Doe");
        updatedCustomer.setPhone("22222222222");

        when(customerRepository.findById(1))
                .thenReturn(Optional.of(existingCustomer));

        when(customerRepository.save(existingCustomer))
                .thenReturn(existingCustomer);

        Customer result = customerService.updateCustomer(1, updatedCustomer);

        assertSame(existingCustomer, result);

        assertEquals("Jane", existingCustomer.getFirstName());
        assertEquals("Doe", existingCustomer.getLastName());
        assertEquals("22222222222", existingCustomer.getPhone());

        verify(customerRepository).findById(1);
        verify(customerRepository).save(existingCustomer);
    }

    @Test
    void updateCustomer_shouldThrowException_whenCustomerDoesNotExist() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");

        when(customerRepository.findById(1))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.updateCustomer(1, customer)
        );

        assertEquals("Customer not found", exception.getMessage());

        verify(customerRepository).findById(1);
        verify(customerRepository, never()).save(any());
    }

    @Test
    void deleteCustomerById_shouldDeleteAndReturnCustomer_whenCustomerExists() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");

        when(customerRepository.findById(1))
                .thenReturn(Optional.of(customer));

        Customer result = customerService.deleteCustomerById(1);

        assertSame(customer, result);

        verify(customerRepository).findById(1);
        verify(customerRepository).delete(customer);
    }

    @Test
    void deleteCustomerById_shouldThrowException_whenCustomerDoesNotExist() {
        when(customerRepository.findById(1))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.deleteCustomerById(1)
        );

        assertEquals("Product not found", exception.getMessage());

        verify(customerRepository).findById(1);
        verify(customerRepository, never()).delete(any());
    }
}