package smartlife.restaurant.menu.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class NutritionInfoTest {

    public static final BigDecimal TEST_VALUE_FOR_MACROS = BigDecimal.valueOf(3.0);
    public static final BigDecimal TEST_VALUE_ENERGY_KILO_CAL = BigDecimal.valueOf(15.0);
    public static final BigDecimal TEST_VALUE_ENERGY_KILO_J = BigDecimal.valueOf(15.0);
    private static final BigDecimal APPLE_FATS_GRAMS = BigDecimal.valueOf(0.17);
    private static final BigDecimal APPLE_SATURATES_GRAMS = BigDecimal.valueOf(0.028);
    private static final BigDecimal APPLE_SUGARS_GRAMS = BigDecimal.valueOf(10.4);
    public static final BigDecimal APPLE_FIBERS_GRAMS = BigDecimal.valueOf(2.4);
    public static final BigDecimal APPLE_CPLX_CARBS_GRAMS = BigDecimal.valueOf(1.2);
    private static final BigDecimal APPLE_PROTEIN_GRAMS = BigDecimal.valueOf(0.26);
    private static final BigDecimal APPLE_SALT_GRAMS = BigDecimal.valueOf(0);

    private void calcLocallyNutritionInfo(BigDecimal fatsGrams,             BigDecimal saturatesGrams,
                                          BigDecimal sugarsGrams,           BigDecimal fibersGrams,
                                          BigDecimal cplxCarbsGrams,        BigDecimal proteinsGrams,
                                          BigDecimal saltGrams,             BigDecimal expectedOtherGrams,
                                          BigDecimal expectedEnergyKiloCal, BigDecimal expectedEnergyKiloJ,
                                          String testInfoText){
        BigDecimal sumOfKnownNutrients = fatsGrams.add(sugarsGrams).add(fibersGrams).add(cplxCarbsGrams).add(proteinsGrams);
        if(!Objects.isNull(saltGrams)){
            sumOfKnownNutrients = sumOfKnownNutrients.add(saltGrams);
        }
        assertEquals(BigDecimal.valueOf(NutritionInfo.BASE_QUANTITY_GRAMS).subtract(sumOfKnownNutrients),
                expectedOtherGrams, testInfoText);

        BigDecimal kiloCalFromFats = fatsGrams.multiply(NutritionInfo.FATS_KILO_CAL_MULTIPLIER);
        BigDecimal kiloCalFromCarbs = (sugarsGrams.add(fibersGrams).add(cplxCarbsGrams))
                .multiply(NutritionInfo.CARBS_KILO_CAL_MULTIPLIER);
        BigDecimal kiloCalFromProtein = proteinsGrams.multiply(NutritionInfo.PROTEINS_KILO_CAL_MULTIPLIER);
        BigDecimal calculatedEnergyKiloCal = kiloCalFromFats.add(kiloCalFromCarbs).add(kiloCalFromProtein);
        BigDecimal calculatedEnergyKiloJ = expectedEnergyKiloCal.multiply(NutritionInfo.KILO_CAL_TO_KILO_J_MULTIPLIER);

        assertEquals(calculatedEnergyKiloCal, expectedEnergyKiloCal, testInfoText);
        assertEquals(calculatedEnergyKiloJ, expectedEnergyKiloJ, testInfoText);
    }
    @Test
    void RequiredArgsConstructor() throws InvalidParameterException {

        NutritionInfo appleNutritionInfo = new NutritionInfo(
                APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS, APPLE_CPLX_CARBS_GRAMS,
                APPLE_PROTEIN_GRAMS, APPLE_SALT_GRAMS) ;

        calcLocallyNutritionInfo(APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, APPLE_SALT_GRAMS,  appleNutritionInfo.getOthersGrams(),
                appleNutritionInfo.getEnergyKiloCal(), appleNutritionInfo.getEnergyKiloJ(),
                new Throwable()
                        .getStackTrace()[0]
                        .getMethodName());
    }

    @Test
    void RequiredArgsConstructorWithNullValues() throws InvalidParameterException {

        NutritionInfo appleNutritionInfo = new NutritionInfo(
                APPLE_FATS_GRAMS, null, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS, APPLE_CPLX_CARBS_GRAMS,
                APPLE_PROTEIN_GRAMS, null) ;

        calcLocallyNutritionInfo(APPLE_FATS_GRAMS, null, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null,  appleNutritionInfo.getOthersGrams(),
                appleNutritionInfo.getEnergyKiloCal(), appleNutritionInfo.getEnergyKiloJ(),
                new Throwable()
                        .getStackTrace()[0]
                        .getMethodName());
    }

    @Test
    void RequiredArgsConstructorWithSalt() throws InvalidParameterException {

        NutritionInfo appleNutritionInfo = new NutritionInfo(
                APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS, APPLE_CPLX_CARBS_GRAMS,
                APPLE_PROTEIN_GRAMS, TEST_VALUE_FOR_MACROS) ;

        calcLocallyNutritionInfo(APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, TEST_VALUE_FOR_MACROS,  appleNutritionInfo.getOthersGrams(),
                appleNutritionInfo.getEnergyKiloCal(), appleNutritionInfo.getEnergyKiloJ(),
                new Throwable()
                        .getStackTrace()[0]
                        .getMethodName());
    }

    @Test
    void requiredArgsConstructorNegativeFats() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        BigDecimal.valueOf(-1), APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                        APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null) );
    }

    @Test
    void requiredArgsConstructorNegativeSaturates() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, BigDecimal.valueOf(-1), APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                        APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null) );
    }

    @Test
    void requiredArgsConstructorNegativeSugars() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, BigDecimal.valueOf(-1), APPLE_FIBERS_GRAMS,
                        APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null) );
    }

    @Test
    void requiredArgsConstructorNegativeFibers() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, BigDecimal.valueOf(-1),
                        APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null) );
    }

    @Test
    void requiredArgsConstructorNegativeCplxCarbs() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                        BigDecimal.valueOf(-1), APPLE_PROTEIN_GRAMS, null) );
    }

    @Test
    void requiredArgsConstructorNegativeProteins() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                        APPLE_CPLX_CARBS_GRAMS, BigDecimal.valueOf(-1), null ));
    }

    @Test
    void requiredArgsConstructorNegativeSalt() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                        APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, BigDecimal.valueOf(-1) ));
    }

    @Test
    void requiredArgsConstructorSaturatedMoreThanFats() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        APPLE_FATS_GRAMS, APPLE_FATS_GRAMS.add(APPLE_SATURATES_GRAMS), APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS,
                        APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, BigDecimal.valueOf(-1) ));
    }

    @Test
    void requiredArgsConstructorSumMoreThanBaseGrams() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class,
                () -> new NutritionInfo(
                        BigDecimal.valueOf(NutritionInfo.BASE_QUANTITY_GRAMS), APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS,
                        APPLE_FIBERS_GRAMS, APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null) );
    }

    @Test
    void allArgsConstructorHappyPath() {

        NutritionInfo appleNutritionInfo = new NutritionInfo(
                TEST_VALUE_ENERGY_KILO_CAL, TEST_VALUE_ENERGY_KILO_J, APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS,
                APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS, APPLE_CPLX_CARBS_GRAMS, APPLE_PROTEIN_GRAMS, null,
                TEST_VALUE_FOR_MACROS, new Ingredient()) ;

        assertEquals(TEST_VALUE_ENERGY_KILO_CAL, appleNutritionInfo.getEnergyKiloCal());
        assertEquals(TEST_VALUE_ENERGY_KILO_J, appleNutritionInfo.getEnergyKiloJ());
        assertEquals(APPLE_FATS_GRAMS, appleNutritionInfo.getFatsGrams());
        assertEquals(APPLE_SATURATES_GRAMS, appleNutritionInfo.getSaturatesGrams());
        assertEquals(APPLE_SUGARS_GRAMS, appleNutritionInfo.getSugarsGrams());
        assertEquals(APPLE_FIBERS_GRAMS, appleNutritionInfo.getFibersGrams());
        assertEquals(APPLE_CPLX_CARBS_GRAMS, appleNutritionInfo.getCplxCarbsGrams());
        assertEquals(APPLE_PROTEIN_GRAMS, appleNutritionInfo.getProteinsGrams());
        assertNull(appleNutritionInfo.getSaltGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getOthersGrams());
    }


    @Test
    void setNutrientsInfoHappyPath() {
        NutritionInfo appleNutritionInfo = new NutritionInfo(
                APPLE_FATS_GRAMS, APPLE_SATURATES_GRAMS, APPLE_SUGARS_GRAMS, APPLE_FIBERS_GRAMS, APPLE_CPLX_CARBS_GRAMS,
                APPLE_PROTEIN_GRAMS, null) ;

        appleNutritionInfo.setNutrientsInfo(TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS,
                TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS);

        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getFatsGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getSaturatesGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getSugarsGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getFibersGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getCplxCarbsGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getProteinsGrams());
        assertEquals(TEST_VALUE_FOR_MACROS, appleNutritionInfo.getSaltGrams());

        calcLocallyNutritionInfo(TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS,
                TEST_VALUE_FOR_MACROS, TEST_VALUE_FOR_MACROS,  TEST_VALUE_FOR_MACROS, appleNutritionInfo.getOthersGrams(),
                appleNutritionInfo.getEnergyKiloCal(), appleNutritionInfo.getEnergyKiloJ(),
                new Throwable()
                        .getStackTrace()[0]
                        .getMethodName());
    }
}