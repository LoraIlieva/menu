package smartlife.restaurant.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Menu extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    Set<MenuContent> containedItems;
}
