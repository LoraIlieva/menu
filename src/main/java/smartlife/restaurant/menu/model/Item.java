package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import smartlife.restaurant.menu.enums.ItemTypes;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "mealItem")
    Set<ItemContent> itemContentSet;

    @NonNull
    @Column(nullable = false)
    String description;

    @Lob
    @Column(nullable = false)
    String instructions;

    @Enumerated(EnumType.STRING)
    ItemTypes itemType;

    //TODO: I think that this relation is not necessary
//    @OneToMany(mappedBy = "containedItem")
//    Set<MenuContent> menuContentSet;
}
