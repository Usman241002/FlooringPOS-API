package ukhalid.dev.flooringpos_api.controllers;

import org.springframework.web.bind.annotation.*;
import ukhalid.dev.flooringpos_api.dtos.CustomerDTO;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.services.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Integer id) {
        customerService.deleteCustomerById(id);
    }
}
