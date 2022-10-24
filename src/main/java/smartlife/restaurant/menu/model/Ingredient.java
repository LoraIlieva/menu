package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartlife.restaurant.menu.enums.Allergens;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {

    @Column (nullable = false)
    private String name;

    @OneToOne (cascade = CascadeType.ALL)
    private NutritionInfo nutritionInfo = new NutritionInfo();

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(nullable = false)
    private Boolean isVegetarian;

    @Column(nullable = false)
    private Boolean isVegan;

    @ElementCollection(targetClass = Allergens.class)
    @CollectionTable(name = "ingredients_allergens", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column (name = "allergen_name")
    @Enumerated(EnumType.STRING)
    private Set<Allergens> allergens = new TreeSet<>();

    //TODO: I think that this relation is not necessary
//    @OneToMany(mappedBy = "containedIngredient")
//    Set<ItemContent> itemContentSet;
}
