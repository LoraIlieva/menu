package smartlife.restaurant.menu.api.v1.mappers;

import lombok.NonNull;
import smartlife.restaurant.menu.api.v1.model.NutritionInfoRequestDTO;
import smartlife.restaurant.menu.api.v1.model.NutritionInfoResponseDTO;
import smartlife.restaurant.menu.model.NutritionInfo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class NutritionInfoMapper {

    //insert new nutritioninfo
    public NutritionInfo NutritionInfoRequestDTOToNutritionInfo(NutritionInfoRequestDTO nutritionInfoRequestDTO){
        NutritionInfo nutritionInfo = new NutritionInfo();
//        if(nutritionInfoRequestDTO.getId() != null)
        nutritionInfo.setNutrientsInfo(
                nutritionInfoRequestDTO.getFatsGrams(),
                nutritionInfo.getSaturatesGrams(),
                nutritionInfoRequestDTO.getSugarsGrams(),
                nutritionInfoRequestDTO.getFibersGrams(),
                nutritionInfoRequestDTO.getCplxCarbsGrams(),
                nutritionInfoRequestDTO.getProteinsGrams(),
                nutritionInfoRequestDTO.getSaltGrams()
        );

        return nutritionInfo;
    }

    public NutritionInfoResponseDTO NutritionInfoToNutritionInfoResponseDTO(NutritionInfo nutritionInfo){

        NutritionInfoResponseDTO nutritionInfoResponseDTO = new NutritionInfoResponseDTO(
                nutritionInfo.getId(),
                nutritionInfo.getEnergyKiloCal(),
                nutritionInfo.getEnergyKiloJ(),
                nutritionInfo.getFatsGrams(),
                nutritionInfo.getSaturatesGrams(),
                nutritionInfo.getSugarsGrams(),
                nutritionInfo.getFibersGrams(),
                nutritionInfo.getCplxCarbsGrams(),
                nutritionInfo.getProteinsGrams(),
                nutritionInfo.getSaltGrams(),
                nutritionInfo.getOthersGrams()
        );
        return nutritionInfoResponseDTO;
    }
}
