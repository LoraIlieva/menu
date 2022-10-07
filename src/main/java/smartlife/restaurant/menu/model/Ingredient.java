package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;
import java.util.Set;
import java.util.TreeSet;

@Data
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends BaseEntity {

    private NutritionInfo nutritionInfo = new NutritionInfo();
    private String description;
    private Boolean isVegetarian;
    private Boolean isVegan;
    private Set<Allergens> allergens = new TreeSet<>();
}
