package ukhalid.dev.flooringpos_api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import ukhalid.dev.flooringpos_api.entities.Address;
import ukhalid.dev.flooringpos_api.entities.Customer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer seedCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Alice");
        customer.setLastName("Wonderland");
        customer.setPhone("07123456789");
        return customerRepository.save(customer);
    }

    private Address seedAddress() {
        Customer customer = seedCustomer();

        Address address = new Address();
        address.setCustomer(customer);
        address.setAddressLine1("123 Maple Street");
        address.setAddressLine2("Flat 4");
        address.setPostcode("B1 2CD");
        return addressRepository.save(address);
    }

    @Test
    public void findById_shouldReturnAddress_whenAddressExists() {
        Address savedAddress = seedAddress();

        Address address = addressRepository.findById(savedAddress.getId()).orElse(null);

        assertThat(address).isNotNull();
        assertThat(address.getAddressLine1()).isEqualTo("123 Maple Street");
        assertThat(address.getAddressLine2()).isEqualTo("Flat 4");
        assertThat(address.getPostcode()).isEqualTo("B1 2CD");
        assertThat(address.getCustomer().getId()).isEqualTo(savedAddress.getCustomer().getId());
    }

    @Test
    public void findById_shouldReturnEmpty_whenAddressDoesNotExist() {
        Address address = addressRepository.findById(-1).orElse(null);

        assertThat(address).isNull();
    }

    @Test
    public void findAll_shouldReturnAllAddresses_whenAddressesExist() {
        Address savedAddress = seedAddress();

        List<Address> addresses = addressRepository.findAll();

        assertThat(addresses).isNotEmpty();
        assertThat(addresses).extracting(Address::getId).contains(savedAddress.getId());
    }

    @Test
    public void save_shouldPersistAddress_whenAddressIsValid() {
        Customer customer = seedCustomer();

        Address address = new Address();
        address.setCustomer(customer);
        address.setAddressLine1("456 Oak Avenue");
        address.setAddressLine2("Suite 200");
        address.setPostcode("B2 3EF");

        Address savedAddress = addressRepository.save(address);
        addressRepository.flush();

        assertThat(addressRepository.findById(savedAddress.getId()))
                .isPresent()
                .get()
                .satisfies(a -> {
                    assertThat(a.getAddressLine1()).isEqualTo("456 Oak Avenue");
                    assertThat(a.getAddressLine2()).isEqualTo("Suite 200");
                    assertThat(a.getPostcode()).isEqualTo("B2 3EF");
                    assertThat(a.getCustomer().getId()).isEqualTo(customer.getId());
                });
    }

    @Test
    public void delete_shouldRemoveAddress_whenAddressExists() {
        Address savedAddress = seedAddress();

        addressRepository.delete(savedAddress);
        addressRepository.flush();

        assertThat(addressRepository.findById(savedAddress.getId()))
                .isEmpty();
    }
}
