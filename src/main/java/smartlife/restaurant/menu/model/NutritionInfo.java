package smartlife.restaurant.menu.model;


import lombok.*;
import smartlife.restaurant.menu.model.superclasses.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nutrition_info")
public class NutritionInfo extends BaseEntity {
    public static final BigDecimal KILO_CAL_TO_KILO_J_MULTIPLIER = BigDecimal.valueOf(4.184);
    public static final BigDecimal FATS_KILO_CAL_MULTIPLIER = BigDecimal.valueOf(4);
    public static final BigDecimal CARBS_KILO_CAL_MULTIPLIER = BigDecimal.valueOf(9);
    public static final BigDecimal PROTEINS_KILO_CAL_MULTIPLIER = BigDecimal.valueOf(4);
    public static final int BASE_QUANTITY_GRAMS = 100;


    @Getter
    @Column(nullable = false)
    private BigDecimal energyKiloCal = BigDecimal.valueOf(0);

    @Getter
    @Column(name = "energy_kilo_j", nullable = false)
    private BigDecimal energyKiloJ = BigDecimal.valueOf(0);

    @NonNull @Getter
    @Column(nullable = false)
    private BigDecimal fatsGrams = BigDecimal.valueOf(0);

    @Getter
    private BigDecimal saturatesGrams = BigDecimal.valueOf(0);

    @NonNull @Getter
    @Column(nullable = false)
    private BigDecimal sugarsGrams = BigDecimal.valueOf(0);

    @NonNull @Getter
    @Column(nullable = false)
    private BigDecimal fibersGrams = BigDecimal.valueOf(0);

    @NonNull @Getter
    @Column(nullable = false)
    private BigDecimal cplxCarbsGrams = BigDecimal.valueOf(0);

    @NonNull @Getter
    @Column(nullable = false)
    private BigDecimal proteinsGrams = BigDecimal.valueOf(0);

    @Getter
    private BigDecimal saltGrams = BigDecimal.valueOf(0);

    @Getter
    private BigDecimal othersGrams = BigDecimal.valueOf(0);

    @NonNull
    @OneToOne
    private Ingredient ingredient;

    public NutritionInfo(@NonNull BigDecimal fatsGrams,         BigDecimal saturatesGrams,
                         @NonNull BigDecimal sugarsGrams,       @NonNull BigDecimal fibersGrams,
                         @NonNull BigDecimal cplxCarbsGrams,    @NonNull BigDecimal proteinsGrams,
                         BigDecimal saltGrams) throws InvalidParameterException {

        validateNutrients  (fatsGrams, saturatesGrams, sugarsGrams, fibersGrams, cplxCarbsGrams,
                            proteinsGrams, saltGrams);

        this.fatsGrams = fatsGrams;
        this.saturatesGrams = saturatesGrams;
        this.sugarsGrams = sugarsGrams;
        this.fibersGrams = fibersGrams;
        this.cplxCarbsGrams = cplxCarbsGrams;
        this.proteinsGrams = proteinsGrams;
        this.saltGrams = saltGrams;


        calculateOtherNutrientQuantity(this);
        calculateEnergyByMacros(this);

    }

    private void validateNutrients(BigDecimal fatsGrams,    BigDecimal saturatesGrams, BigDecimal sugarsGrams,
                                   BigDecimal fibersGrams,  BigDecimal cplxCarbsGrams, BigDecimal proteinsGrams,
                                   BigDecimal saltGrams) throws  InvalidParameterException{

        if(     fatsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                (!Objects.isNull(saturatesGrams) && saturatesGrams.compareTo(BigDecimal.valueOf(0)) < 0) ||
                sugarsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                fibersGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                cplxCarbsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                proteinsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                (!Objects.isNull(saltGrams) && saltGrams.compareTo(BigDecimal.valueOf(0)) < 0)
        ){
            throw new InvalidParameterException("Nutrient's quantities invalid - negative value");
        }

        if(!Objects.isNull(saturatesGrams) && saturatesGrams.compareTo(fatsGrams) > 0) {
            throw new InvalidParameterException("Saturates grams are more than fats grams");
        }

        BigDecimal sumOfNutrients = fatsGrams.add(sugarsGrams).add(fibersGrams).add(cplxCarbsGrams).add(proteinsGrams);

        if(!Objects.isNull(saltGrams)) {
            sumOfNutrients = sumOfNutrients.add(saltGrams);
        }

        if(sumOfNutrients.compareTo(BigDecimal.valueOf(BASE_QUANTITY_GRAMS)) > 0) {
            throw new InvalidParameterException("Nutrient's quantities invalid - sum is more than " + BASE_QUANTITY_GRAMS
                    + "grams");
        }
    }

    private void calculateOtherNutrientQuantity(NutritionInfo obj){
        BigDecimal sumOfNutrients = fatsGrams.add(sugarsGrams).add(fibersGrams).add(cplxCarbsGrams).add(proteinsGrams);

        if(!Objects.isNull(saltGrams)) {
            sumOfNutrients = sumOfNutrients.add(saltGrams);
        }

        obj.othersGrams = BigDecimal.valueOf(NutritionInfo.BASE_QUANTITY_GRAMS)
                .subtract(sumOfNutrients);
    }

    private void calculateEnergyByMacros(NutritionInfo obj){
        BigDecimal kiloCalFromFats = obj.fatsGrams.multiply(FATS_KILO_CAL_MULTIPLIER);
        BigDecimal kiloCalFromCarbs = obj.sugarsGrams.add(obj.fibersGrams).add(obj.cplxCarbsGrams).multiply(CARBS_KILO_CAL_MULTIPLIER);
        BigDecimal kiloCalFromProtein = obj.proteinsGrams.multiply(PROTEINS_KILO_CAL_MULTIPLIER);
        obj.energyKiloCal = kiloCalFromFats.add(kiloCalFromCarbs).add(kiloCalFromProtein);
        obj.energyKiloJ = energyKiloCal.multiply(KILO_CAL_TO_KILO_J_MULTIPLIER);
    }


    public void setNutrientsInfo(BigDecimal fatsGrams,      BigDecimal saturesGrams,    BigDecimal sugarsGrams,
                                 BigDecimal fibersGrams,    BigDecimal cplxCarbsGrams,  BigDecimal proteinsGrams,
                                 BigDecimal saltGrams) {
        validateNutrients(fatsGrams, saturesGrams, sugarsGrams, fibersGrams, cplxCarbsGrams, proteinsGrams, saltGrams);

        this.fatsGrams = fatsGrams;
        this.saturatesGrams = saturesGrams;
        this.sugarsGrams = sugarsGrams;
        this.fibersGrams = fibersGrams;
        this.cplxCarbsGrams = cplxCarbsGrams;
        this.proteinsGrams = proteinsGrams;
        this.saltGrams = saltGrams;

        calculateOtherNutrientQuantity(this);
        calculateEnergyByMacros(this);
    }



}
