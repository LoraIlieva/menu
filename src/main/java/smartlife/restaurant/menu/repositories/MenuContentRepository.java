package smartlife.restaurant.menu.repositories;

import org.springframework.data.repository.CrudRepository;
import smartlife.restaurant.menu.model.MenuContent;

public interface MenuContentRepository extends CrudRepository<MenuContent, Long> {
}
