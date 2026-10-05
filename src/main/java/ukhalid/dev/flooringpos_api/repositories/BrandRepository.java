package ukhalid.dev.flooringpos_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukhalid.dev.flooringpos_api.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
