package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.repositories.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }
    
    public Customer deleteCustomerById(Integer id) {
        Optional<Customer> customerToDeleteOptional = customerRepository.findById(id);
        if (customerToDeleteOptional.isPresent()) {
            Customer customerToDelete = customerToDeleteOptional.get();
            customerRepository.delete(customerToDelete);
            return customerToDelete;
        }
        throw new IllegalArgumentException("Product not found");
    }

}



