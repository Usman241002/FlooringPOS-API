package ukhalid.dev.flooringpos_api.controllers;

import org.springframework.web.bind.annotation.*;
import ukhalid.dev.flooringpos_api.dtos.CreateAddressDTO;
import ukhalid.dev.flooringpos_api.entities.Address;
import ukhalid.dev.flooringpos_api.services.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Address createAddress(@RequestBody CreateAddressDTO address) {
        return addressService.createAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable int id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public Address deleteAddress(@PathVariable int id) {
        return addressService.deleteAddress(id);
    }
}
