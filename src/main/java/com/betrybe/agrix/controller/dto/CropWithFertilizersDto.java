package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Crop with fertilizers dto.
 */
public record CropWithFertilizersDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId,
    List<FertilizerDto> fertilizers
) {

  /**
   * From entity crop with fertilizers dto.
   *
   * @param crop the crop
   * @return the crop with fertilizers dto
   */
  public static CropWithFertilizersDto fromEntity(Crop crop) {
    return new CropWithFertilizersDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId(),
        crop.getFertilizers().stream()
            .map(FertilizerDto::fromEntity)
            .toList()
    );
  }

}
