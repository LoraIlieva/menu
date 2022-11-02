package smartlife.restaurant.menu.repositories;

import org.springframework.data.repository.CrudRepository;
import smartlife.restaurant.menu.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
