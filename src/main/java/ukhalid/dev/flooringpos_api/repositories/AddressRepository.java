package ukhalid.dev.flooringpos_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukhalid.dev.flooringpos_api.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
