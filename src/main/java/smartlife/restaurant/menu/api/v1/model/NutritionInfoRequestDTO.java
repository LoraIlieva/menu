package smartlife.restaurant.menu.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class NutritionInfoRequestDTO {

//    private Long id;

    @NonNull
    @NotNull(message = "fatsGrams is a required property")
    private BigDecimal fatsGrams;

    @NonNull
    @NotNull(message = "saturatesGrams is a required property")
    private BigDecimal saturatesGrams;

    @NonNull
    @NotNull(message = "sugarsGrams is a required property")
    private BigDecimal sugarsGrams;

    @NonNull
    @NotNull(message = "fibersGrams is a required property")
    private BigDecimal fibersGrams;

    @NonNull
    @NotNull(message = "cplxCarbsGrams is a required property")
    private BigDecimal cplxCarbsGrams;

    @NonNull
    @NotNull(message = "proteinsGrams is a required property")
    private BigDecimal proteinsGrams;

    @NonNull
    @NotNull(message = "saltGrams is a required property")
    private BigDecimal saltGrams;
}
