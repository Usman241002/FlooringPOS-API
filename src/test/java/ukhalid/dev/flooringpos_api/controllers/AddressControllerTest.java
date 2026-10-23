package ukhalid.dev.flooringpos_api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ukhalid.dev.flooringpos_api.entities.Address;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.services.AddressService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPhone("07700900123");

        address = new Address();
        address.setId(1);
        address.setAddressLine1("14 High Street");
        address.setAddressLine2("Apartment 2B");
        address.setPostcode("B25 8AB");
        address.setCustomer(customer);
    }

    // POST /addresses

    @Test
    void createAddress_shouldReturnCreatedAddress() throws Exception {
        when(addressService.createAddress(any()))
                .thenReturn(address);

        String requestBody = """
                {
                    "customerId": 1,
                    "addressLine1": "14 High Street",
                    "addressLine2": "Apartment 2B",
                    "postcode": "B25 8AB"
                }
                """;

        mockMvc.perform(post("/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.addressLine1").value("14 High Street"))
                .andExpect(jsonPath("$.addressLine2").value("Apartment 2B"))
                .andExpect(jsonPath("$.postcode").value("B25 8AB"));

        verify(addressService).createAddress(any());
    }

    // PUT /addresses/{id}

    @Test
    void updateAddress_shouldReturnUpdatedAddress() throws Exception {
        address.setAddressLine1("Updated High Street");
        address.setAddressLine2("Suite 10");
        address.setPostcode("B25 9ZZ");

        when(addressService.updateAddress(eq(1), any(Address.class)))
                .thenReturn(address);

        String requestBody = """
                {
                    "addressLine1": "Updated High Street",
                    "addressLine2": "Suite 10",
                    "postcode": "B25 9ZZ"
                }
                """;

        mockMvc.perform(put("/addresses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.addressLine1").value("Updated High Street"))
                .andExpect(jsonPath("$.addressLine2").value("Suite 10"))
                .andExpect(jsonPath("$.postcode").value("B25 9ZZ"));

        verify(addressService).updateAddress(eq(1), any(Address.class));
    }

    // DELETE /addresses/{id}

    @Test
    void deleteAddress_shouldDeleteAddress() throws Exception {
        mockMvc.perform(delete("/addresses/1"))
                .andExpect(status().isOk());

        verify(addressService).deleteAddress(1);
    }
}
