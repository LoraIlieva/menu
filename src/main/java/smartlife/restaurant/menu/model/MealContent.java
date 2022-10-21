package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class MealContent extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="meal_item_id", nullable=false)
    Item mealItem;

    @ManyToOne
    @JoinColumn(name="contained_ingredient_id")
    Ingredient containedIngredient;

    @ManyToOne
    @JoinColumn(name="contained_item_id")
    Item containedItem;

    Integer quantity = 0;

    @Enumerated(EnumType.STRING)
    Units unit;
}
