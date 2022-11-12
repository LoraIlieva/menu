package smartlife.restaurant.menu.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class NutritionInfoResponseDTO {
    private Long id;
    private BigDecimal energyKiloCal;
    private BigDecimal energyKiloJ;
    private BigDecimal fatsGrams;
    private BigDecimal saturatesGrams;
    private BigDecimal sugarsGrams;
    private BigDecimal fibersGrams;
    private BigDecimal cplxCarbsGrams;
    private BigDecimal proteinsGrams;
    private BigDecimal saltGrams;
    private BigDecimal othersGrams;
}
