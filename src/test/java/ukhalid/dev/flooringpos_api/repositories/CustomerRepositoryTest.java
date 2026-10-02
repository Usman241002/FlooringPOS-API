package ukhalid.dev.flooringpos_api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import ukhalid.dev.flooringpos_api.entities.Customer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void save_shouldSaveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("01234567890");

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getId());
        assertEquals("John", savedCustomer.getFirstName());
        assertEquals("Smith", savedCustomer.getLastName());
        assertEquals("01234567890", savedCustomer.getPhone());
    }

    @Test
    void findById_shouldReturnCustomer_whenCustomerExists() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("01234567890");

        Customer savedCustomer = customerRepository.save(customer);

        Optional<Customer> result =
                customerRepository.findById(savedCustomer.getId());

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
        assertEquals("Smith", result.get().getLastName());
        assertEquals("01234567890", result.get().getPhone());
    }

    @Test
    void findById_shouldReturnEmpty_whenCustomerDoesNotExist() {
        Optional<Customer> result =
                customerRepository.findById(999);

        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_shouldReturnAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Smith");

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<Customer> result = customerRepository.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void delete_shouldDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");

        Customer savedCustomer = customerRepository.save(customer);

        customerRepository.delete(savedCustomer);

        Optional<Customer> result =
                customerRepository.findById(savedCustomer.getId());

        assertTrue(result.isEmpty());
    }
}