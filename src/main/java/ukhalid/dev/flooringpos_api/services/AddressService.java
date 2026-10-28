package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.dtos.CreateAddressDTO;
import ukhalid.dev.flooringpos_api.entities.Address;
import ukhalid.dev.flooringpos_api.entities.Customer;
import ukhalid.dev.flooringpos_api.repositories.AddressRepository;
import ukhalid.dev.flooringpos_api.repositories.CustomerRepository;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public Address getAddressById(int id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            return addressOptional.get();
        }
        throw new IllegalArgumentException("Address not found");
    }

    public Address createAddress(CreateAddressDTO address) {
        Address newAddress = new Address();
        Optional<Customer> customerOptional = customerRepository.findById(address.getCustomerId());
        if (customerOptional.isPresent()) {
            newAddress.setLine1(address.getLine1());
            newAddress.setLine2(address.getLine2());
            newAddress.setPostcode(address.getPostcode());
            newAddress.setCustomer(customerOptional.get());
            return addressRepository.save(newAddress);
        }
        throw new IllegalArgumentException("Customer not found");
    }

    public Address updateAddress(Integer addressId, Address address) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);

        if (addressOptional.isPresent()) {
            Address existingAddress = addressOptional.get();

            existingAddress.setLine1(address.getLine1());
            existingAddress.setLine2(address.getLine2());
            existingAddress.setPostcode(address.getPostcode());

            return addressRepository.save(existingAddress);
        }

        throw new IllegalArgumentException("Address not found");
    }

    public void deleteAddress(Integer id) {
        Optional<Address> addressToDeleteOptional = addressRepository.findById(id);
        if (addressToDeleteOptional.isEmpty()) {
            throw new IllegalArgumentException("Address not found");
        }
        addressRepository.deleteById(id);
    }


}
