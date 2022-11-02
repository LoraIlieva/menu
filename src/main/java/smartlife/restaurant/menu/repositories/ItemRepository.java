package smartlife.restaurant.menu.repositories;

import org.springframework.data.repository.CrudRepository;
import smartlife.restaurant.menu.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
