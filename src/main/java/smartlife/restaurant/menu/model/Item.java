package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseEntity {
    Map<Ingredient, Integer> ingredients = new HashMap<>();
    Map<Item, Integer> items = new HashMap<>();
    String description;
    String instructions;
    ItemType itemType;

}
