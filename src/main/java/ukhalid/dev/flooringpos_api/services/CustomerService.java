package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.dtos.CustomerDTO;
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

    public void deleteCustomerById(Integer id) {
        Optional<Customer> customerToDeleteOptional = customerRepository.findById(id);
        if (customerToDeleteOptional.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        Customer customerToDelete = customerToDeleteOptional.get();
        customerRepository.delete(customerToDelete);
    }

    public Customer createCustomer(CustomerDTO customer) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setPhone(customer.getPhone());

        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(Integer id, CustomerDTO customer) {
        Optional<Customer> customerToUpdateOptional = customerRepository.findById(id);
        if (customerToUpdateOptional.isPresent()) {
            Customer customerToUpdate = customerToUpdateOptional.get();
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setLastName(customer.getLastName());
            customerToUpdate.setPhone(customer.getPhone());
            return customerRepository.save(customerToUpdate);
        }
        throw new IllegalArgumentException("Customer not found");
    }
}



