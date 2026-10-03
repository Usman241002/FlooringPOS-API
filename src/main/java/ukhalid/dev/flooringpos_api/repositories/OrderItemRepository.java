package ukhalid.dev.flooringpos_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukhalid.dev.flooringpos_api.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
