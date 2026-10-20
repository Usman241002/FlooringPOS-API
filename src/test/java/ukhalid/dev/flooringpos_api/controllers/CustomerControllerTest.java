package ukhalid.dev.flooringpos_api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.services.CustomerService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("07700900123");
    }

    // GET /customers

    @Test
    void getAllCustomers_shouldReturnCustomers() throws Exception {
        when(customerService.getAllCustomers())
                .thenReturn(List.of(customer));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[0].phone").value("07700900123"));

        verify(customerService).getAllCustomers();
    }

    @Test
    void getAllCustomers_shouldReturnEmptyArray_whenNoCustomersExist() throws Exception {
        when(customerService.getAllCustomers())
                .thenReturn(List.of());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(customerService).getAllCustomers();
    }

    // GET /customers/{id}

    @Test
    void getCustomerById_shouldReturnCustomer() throws Exception {
        when(customerService.getCustomerById(1))
                .thenReturn(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.phone").value("07700900123"));

        verify(customerService).getCustomerById(1);
    }

    // POST /customers

    @Test
    void createCustomer_shouldReturnCreatedCustomer() throws Exception {
        when(customerService.createCustomer(any()))
                .thenReturn(customer);

        String requestBody = """
                {
                    "firstName": "John",
                    "lastName": "Smith",
                    "phone": "07700900123"
                }
                """;

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.phone").value("07700900123"));

        verify(customerService).createCustomer(any());
    }

    // PUT /customers/{id}

    @Test
    void updateCustomer_shouldReturnUpdatedCustomer() throws Exception {
        customer.setFirstName("Johnny");
        customer.setLastName("Doe");
        customer.setPhone("07700900999");

        when(customerService.updateCustomer(eq(1), any()))
                .thenReturn(customer);

        String requestBody = """
                {
                    "firstName": "Johnny",
                    "lastName": "Doe",
                    "phone": "07700900999"
                }
                """;

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Johnny"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.phone").value("07700900999"));

        verify(customerService).updateCustomer(eq(1), any());
    }

    // DELETE /customers/{id}

    @Test
    void deleteCustomerById_shouldReturnDeletedCustomer() throws Exception {
        when(customerService.deleteCustomerById(1))
                .thenReturn(customer);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"));

        verify(customerService).deleteCustomerById(1);
    }
}
