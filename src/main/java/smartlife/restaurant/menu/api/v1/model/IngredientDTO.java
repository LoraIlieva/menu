package smartlife.restaurant.menu.api.v1.model;
import lombok.Data;
import smartlife.restaurant.menu.enums.Allergens;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.TreeSet;


@Data
public class IngredientDTO {

    @Positive(message = "ID must be positive")
    private Long id;

    @NotNull(message = "Name is a required property")
    private String name;

    @NotNull(message = "Nutrition info is required")
    NutritionInfoRequestDTO nutritionInfo;

    @NotNull(message = "Description is a required property")
    private String description;

    @NotNull(message = "isVegetarian is a required property")
    private Boolean isVegetarian;

    @NotNull(message = "isVegan is a required property")
    private Boolean isVegan;

    private Set<Allergens> allergens = new TreeSet<>();



}
