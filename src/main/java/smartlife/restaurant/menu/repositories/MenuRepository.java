package smartlife.restaurant.menu.repositories;

import org.springframework.data.repository.CrudRepository;
import smartlife.restaurant.menu.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {
}
