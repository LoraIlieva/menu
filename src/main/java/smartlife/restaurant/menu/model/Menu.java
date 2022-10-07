package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {
    Map<ItemType, ArrayList<Item>> items = new HashMap<>();
}
