package smartlife.restaurant.menu.model;

import smartlife.restaurant.menu.enums.ItemTypes;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;

@Entity
public class MenuContent extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="menu_id", nullable=false)
    Menu menu;

    @Enumerated(EnumType.STRING)
    ItemTypes itemType;

    @ManyToOne
    @JoinColumn(name="contained_item_id")
    Item containedItem;
}
