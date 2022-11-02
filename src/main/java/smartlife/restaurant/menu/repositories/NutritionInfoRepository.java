package smartlife.restaurant.menu.repositories;

import org.springframework.data.repository.CrudRepository;
import smartlife.restaurant.menu.model.NutritionInfo;

public interface NutritionInfoRepository extends CrudRepository<NutritionInfo, Long> {
}
