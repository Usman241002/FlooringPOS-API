package ukhalid.dev.flooringpos_api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ukhalid.dev.flooringpos_api.dtos.CustomerDTO;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("07700900123");

        customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Smith");
        customerDTO.setPhone("07700900123");
    }

    @Test
    void getAllCustomers_shouldReturnAllCustomers() {
        List<Customer> customerList = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customerList);

        Iterable<Customer> result = customerService.getAllCustomers();

        assertThat(result).containsExactly(customer);
        verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById_shouldReturnCustomer_whenCustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1);

        assertThat(result).isSameAs(customer);
        verify(customerRepository).findById(1);
    }

    @Test
    void getCustomerById_shouldReturnNull_whenCustomerDoesNotExist() {
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        Customer result = customerService.getCustomerById(99);

        assertThat(result).isNull();
        verify(customerRepository).findById(99);
    }

    @Test
    void createCustomer_shouldSaveAndReturnCustomer() {
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.createCustomer(customerDTO);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("John");
        assertThat(result.getLastName()).isEqualTo("Smith");
        assertThat(result.getPhone()).isEqualTo("07700900123");

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void updateCustomer_shouldUpdateAndReturnCustomer_whenCustomerExists() {
        CustomerDTO updateDTO = new CustomerDTO();
        updateDTO.setFirstName("Johnny");
        updateDTO.setLastName("Doe");
        updateDTO.setPhone("07700900999");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.updateCustomer(1, updateDTO);

        assertThat(result.getFirstName()).isEqualTo("Johnny");
        assertThat(result.getLastName()).isEqualTo("Doe");
        assertThat(result.getPhone()).isEqualTo("07700900999");

        verify(customerRepository).findById(1);
        verify(customerRepository).save(customer);
    }

    @Test
    void updateCustomer_shouldThrowException_whenCustomerDoesNotExist() {
        CustomerDTO updateDTO = new CustomerDTO();
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.updateCustomer(99, updateDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Customer not found");

        verify(customerRepository).findById(99);
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void deleteCustomerById_shouldDeleteCustomer_whenCustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        customerService.deleteCustomerById(1);

        verify(customerRepository).findById(1);
        verify(customerRepository).delete(customer);
    }

    @Test
    void deleteCustomerById_shouldThrowException_whenCustomerDoesNotExist() {
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.deleteCustomerById(99)
        );

        assertThat(exception.getMessage()).isEqualTo("Customer not found");

        verify(customerRepository).findById(99);
        verify(customerRepository, never()).delete(any(Customer.class));
    }
}
