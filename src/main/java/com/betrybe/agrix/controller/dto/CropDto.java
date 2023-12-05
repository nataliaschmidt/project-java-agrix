package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate, Long farmId) {

  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );
  }

  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setPlantedDate(plantedDate);
    crop.setHarvestDate(harvestDate);
    return crop;
  }

}
