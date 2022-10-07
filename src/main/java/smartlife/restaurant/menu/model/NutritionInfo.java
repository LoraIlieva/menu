package smartlife.restaurant.menu.model;


import lombok.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

@NoArgsConstructor
@AllArgsConstructor
public class NutritionInfo {
    public static final BigDecimal KILO_CAL_TO_KILO_J_MULTIPLIER = BigDecimal.valueOf(4.184);
    public static final BigDecimal FATS_KILO_CAL_MULTIPLIER = BigDecimal.valueOf(4);
    public static final BigDecimal CARBS_KILO_CAL_MULTIPLIER = BigDecimal.valueOf(9);
    public static final BigDecimal PROTEINS_KILO_CAL_MULTIPLIER = BigDecimal.valueOf(4);
    public static final int BASE_QUANTITY_GRAMS = 100;

    @Getter
    private BigDecimal energyKiloCal = BigDecimal.valueOf(0);

    @Getter
    private BigDecimal energyKiloJ = BigDecimal.valueOf(0);

    @NonNull @Getter
    private BigDecimal fatsGrams = BigDecimal.valueOf(0);

    @NonNull @Getter
    private BigDecimal sugarsGrams = BigDecimal.valueOf(0);
    @NonNull @Getter
    private BigDecimal fibersGrams = BigDecimal.valueOf(0);
    @NonNull @Getter
    private BigDecimal cplxCarbsGrams = BigDecimal.valueOf(0);

    @NonNull @Getter
    private BigDecimal proteinsGrams = BigDecimal.valueOf(0);

    @Getter
    private BigDecimal othersGrams = new BigDecimal(0);

    public NutritionInfo(@NonNull BigDecimal fatsGrams,     @NonNull BigDecimal sugarsGrams,
                         @NonNull BigDecimal fibersGrams,   @NonNull BigDecimal cplxCarbsGrams,
                         @NonNull BigDecimal proteinsGrams) throws InvalidParameterException {

        validateNutrients(fatsGrams, sugarsGrams, fibersGrams, cplxCarbsGrams, proteinsGrams);

        this.fatsGrams = fatsGrams;
        this.sugarsGrams = sugarsGrams;
        this.fibersGrams = fibersGrams;
        this.cplxCarbsGrams = cplxCarbsGrams;
        this.proteinsGrams = proteinsGrams;


        calculateOtherNutrientQuantity(this);
        calculateEnergyByMacros(this);

    }

    private void validateNutrients(BigDecimal fatsGrams, BigDecimal sugarsGrams,
                                   BigDecimal fibersGrams, BigDecimal cplxCarbsGrams,
                                   BigDecimal proteinsGrams) throws  InvalidParameterException{
        if(     fatsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                sugarsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                fibersGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                cplxCarbsGrams.compareTo(BigDecimal.valueOf(0)) < 0 ||
                proteinsGrams.compareTo(BigDecimal.valueOf(0)) < 0
        ){
            throw new InvalidParameterException("Nutrient's quantities invalid - negative value");
        }

        if(fatsGrams.add(sugarsGrams).add(fibersGrams).add(cplxCarbsGrams).add(proteinsGrams)
                .compareTo(BigDecimal.valueOf(BASE_QUANTITY_GRAMS)) > 0) {
            throw new InvalidParameterException("Nutrient's quantities invalid - sum is more than " + BASE_QUANTITY_GRAMS  + "grams");
        }
    }

    private void calculateOtherNutrientQuantity(NutritionInfo obj){
        obj.othersGrams = BigDecimal.valueOf(NutritionInfo.BASE_QUANTITY_GRAMS)
                .subtract(obj.fatsGrams.add(obj.sugarsGrams).add(obj.fibersGrams).add(obj.cplxCarbsGrams).add(obj.proteinsGrams));
    }

    private void calculateEnergyByMacros(NutritionInfo obj){
        BigDecimal kiloCalFromFats = obj.fatsGrams.multiply(FATS_KILO_CAL_MULTIPLIER);
        BigDecimal kiloCalFromCarbs = obj.sugarsGrams.add(obj.fibersGrams).add(obj.cplxCarbsGrams).multiply(CARBS_KILO_CAL_MULTIPLIER);
        BigDecimal kiloCalFromProtein = obj.proteinsGrams.multiply(PROTEINS_KILO_CAL_MULTIPLIER);
        obj.energyKiloCal = kiloCalFromFats.add(kiloCalFromCarbs).add(kiloCalFromProtein);
        obj.energyKiloJ = energyKiloCal.multiply(KILO_CAL_TO_KILO_J_MULTIPLIER);
    }

    //fatsGrams, sugarsGrams, fibersGrams, cplxCarbsGrams, proteinsGrams
    public void setNutrientsInfo(BigDecimal fatsGrams, BigDecimal sugarsGrams, BigDecimal fibersGrams,
                                 BigDecimal cplxCarbsGrams, BigDecimal proteinsGrams) {
        validateNutrients(fatsGrams, sugarsGrams, fibersGrams, cplxCarbsGrams, proteinsGrams);

        this.fatsGrams = fatsGrams;
        this.sugarsGrams = sugarsGrams;
        this.fibersGrams = fibersGrams;
        this.cplxCarbsGrams = cplxCarbsGrams;
        this.proteinsGrams = proteinsGrams;

        calculateOtherNutrientQuantity(this);
        calculateEnergyByMacros(this);
    }



}
