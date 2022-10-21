package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Item extends BaseEntity {

    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "mealItem")
    Set<MealContent> mealContentSet;


    @NonNull
    @Column(nullable = false)
    String description;

    @Lob
    @Column(nullable = false)
    String instructions;

    @Enumerated(EnumType.STRING)
    ItemType itemType;

    @OneToMany(mappedBy = "containedItem")
    Set<MenuContent> menuContentSet;
}
