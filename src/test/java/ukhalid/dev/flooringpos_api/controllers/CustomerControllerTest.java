package ukhalid.dev.flooringpos_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.services.CustomerService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CustomerService customerService;

    @Test
    void getAllCustomers_shouldReturnCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Smith");

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");

        when(customerService.getAllCustomers())
                .thenReturn(List.of(customer1, customer2));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"));

        verify(customerService).getAllCustomers();
    }

    @Test
    void getCustomerById_shouldReturnCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("01234567890");

        when(customerService.getCustomerById(1))
                .thenReturn(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.phone").value("01234567890"));

        verify(customerService).getCustomerById(1);
    }

    @Test
    void createCustomer_shouldReturnCreatedCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("01234567890");

        when(customerService.createCustomer(any(Customer.class)))
                .thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.phone").value("01234567890"));

        verify(customerService).createCustomer(any(Customer.class));
    }

    @Test
    void updateCustomer_shouldReturnUpdatedCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPhone("09876543210");

        when(customerService.updateCustomer(eq(1), any(Customer.class)))
                .thenReturn(customer);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.phone").value("09876543210"));

        verify(customerService).updateCustomer(eq(1), any(Customer.class));
    }

    @Test
    void deleteCustomerById_shouldReturnNoContent() throws Exception {
        when(customerService.deleteCustomerById(1))
                .thenReturn(null);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(1);
    }
}