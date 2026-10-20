package ukhalid.dev.flooringpos_api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import ukhalid.dev.flooringpos_api.entities.Customer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer seedCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Alice");
        customer.setLastName("Wonderland");
        customer.setPhone("07123456789");
        return customerRepository.save(customer);
    }

    @Test
    public void findById_shouldReturnCustomer_whenCustomerExists() {
        Customer savedCustomer = seedCustomer();

        Customer customer = customerRepository.findById(savedCustomer.getId()).orElse(null);

        assertThat(customer).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo("Alice");
        assertThat(customer.getLastName()).isEqualTo("Wonderland");
        assertThat(customer.getPhone()).isEqualTo("07123456789");
    }

    @Test
    public void findById_shouldReturnEmpty_whenCustomerDoesNotExist() {
        Customer customer = customerRepository.findById(-1).orElse(null);

        assertThat(customer).isNull();
    }

    @Test
    public void findAll_shouldReturnAllCustomers_whenCustomersExist() {
        Customer savedCustomer = seedCustomer();

        List<Customer> customers = customerRepository.findAll();

        assertThat(customers).isNotEmpty();
        assertThat(customers).extracting(Customer::getId).contains(savedCustomer.getId());
    }

    @Test
    public void save_shouldPersistCustomer_whenCustomerIsValid() {
        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Builder");
        customer.setPhone("07987654321");

        Customer savedCustomer = customerRepository.save(customer);
        customerRepository.flush();

        assertThat(customerRepository.findById(savedCustomer.getId()))
                .isPresent()
                .get()
                .satisfies(c -> {
                    assertThat(c.getFirstName()).isEqualTo("Bob");
                    assertThat(c.getLastName()).isEqualTo("Builder");
                    assertThat(c.getPhone()).isEqualTo("07987654321");
                });
    }

    @Test
    public void delete_shouldRemoveCustomer_whenCustomerExists() {
        Customer savedCustomer = seedCustomer();

        customerRepository.delete(savedCustomer);
        customerRepository.flush();

        assertThat(customerRepository.findById(savedCustomer.getId()))
                .isEmpty();
    }
}
