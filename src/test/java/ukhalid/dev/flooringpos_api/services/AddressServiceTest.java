package ukhalid.dev.flooringpos_api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ukhalid.dev.flooringpos_api.dtos.CreateAddressDTO;
import ukhalid.dev.flooringpos_api.entities.Address;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.repositories.AddressRepository;
import ukhalid.dev.flooringpos_api.repositories.CustomerRepository;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AddressService addressService;

    private Customer customer;
    private Address address;
    private CreateAddressDTO createAddressDTO;

    @BeforeEach
    void setUp() throws Exception {
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setPhone("07700900123");

        address = new Address();
        address.setId(1);
        address.setAddressLine1("14 High Street");
        address.setAddressLine2("Apartment 2B");
        address.setPostcode("B25 8AB");
        address.setCustomer(customer);

        createAddressDTO = new CreateAddressDTO();
        setField(createAddressDTO, "customerId", 1);
        setField(createAddressDTO, "addressLine1", "14 High Street");
        setField(createAddressDTO, "addressLine2", "Apartment 2B");
        setField(createAddressDTO, "postcode", "B25 8AB");
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void createAddress_shouldSaveAndReturnAddress_whenCustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = addressService.createAddress(createAddressDTO);

        assertThat(result).isNotNull();
        assertThat(result.getAddressLine1()).isEqualTo("14 High Street");
        assertThat(result.getAddressLine2()).isEqualTo("Apartment 2B");
        assertThat(result.getPostcode()).isEqualTo("B25 8AB");
        assertThat(result.getCustomer()).isSameAs(customer);

        verify(customerRepository).findById(1);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void createAddress_shouldThrowException_whenCustomerDoesNotExist() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> addressService.createAddress(createAddressDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Customer not found");

        verify(customerRepository).findById(1);
        verify(addressRepository, never()).save(any(Address.class));
    }

    @Test
    void updateAddress_shouldUpdateAndReturnAddress_whenAddressExists() {
        Address updateInfo = new Address();
        updateInfo.setAddressLine1("Updated Line 1");
        updateInfo.setAddressLine2("Updated Line 2");
        updateInfo.setPostcode("B99 9ZZ");

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = addressService.updateAddress(1, updateInfo);

        assertThat(result.getAddressLine1()).isEqualTo("Updated Line 1");
        assertThat(result.getAddressLine2()).isEqualTo("Updated Line 2");
        assertThat(result.getPostcode()).isEqualTo("B99 9ZZ");

        verify(addressRepository).findById(1);
        verify(addressRepository).save(address);
    }

    @Test
    void updateAddress_shouldThrowException_whenAddressDoesNotExist() {
        Address updateInfo = new Address();
        when(addressRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> addressService.updateAddress(99, updateInfo)
        );

        assertThat(exception.getMessage()).isEqualTo("Address not found");

        verify(addressRepository).findById(99);
        verify(addressRepository, never()).save(any(Address.class));
    }

    @Test
    void deleteAddress_shouldDeleteAndReturnAddress_whenAddressExists() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Address result = addressService.deleteAddress(1);

        assertThat(result).isSameAs(address);

        verify(addressRepository).findById(1);
        verify(addressRepository).deleteById(1);
    }

    @Test
    void deleteAddress_shouldThrowException_whenAddressDoesNotExist() {
        when(addressRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> addressService.deleteAddress(99)
        );

        assertThat(exception.getMessage()).isEqualTo("Address not found");

        verify(addressRepository).findById(99);
        verify(addressRepository, never()).deleteById(anyInt());
    }
}
