package ukhalid.dev.flooringpos_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukhalid.dev.flooringpos_api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
