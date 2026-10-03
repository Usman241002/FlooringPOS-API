package ukhalid.dev.flooringpos_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukhalid.dev.flooringpos_api.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
