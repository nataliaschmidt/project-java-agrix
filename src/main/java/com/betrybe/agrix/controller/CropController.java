package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exceptions.CropNotFoundExcepction;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   * @throws CropNotFoundExcepction the crop not found excepction
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> findById(@PathVariable("id") Long id)
      throws CropNotFoundExcepction {
    Crop crop = cropService.findById(id);
    return ResponseEntity.ok(CropDto.fromEntity(crop));
  }

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> findAll() {
    List<Crop> allCrops = cropService.findAll();
    List<CropDto> data = allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Gets crops to harvest date interval.
   *
   * @param start the start
   * @param end   the end
   * @return the crops to harvest date interval
   * @throws CropNotFoundExcepction the crop not found excepction
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>>
      getCropsToHarvestDateInterval(@RequestParam LocalDate start, @RequestParam LocalDate end)
      throws CropNotFoundExcepction {
    List<Crop> allCrops = cropService.getCropsToHarvestDateInterval(start, end);
    List<CropDto> data = allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Associate crop and fertilizer response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   * @throws CropNotFoundExcepction      the crop not found excepction
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropAndFertilizer(
      @PathVariable long cropId, @PathVariable long fertilizerId
  )
      throws CropNotFoundExcepction, FertilizerNotFoundException {
    String message = cropService.associateCropAndFertilizer(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   * @throws CropNotFoundExcepction the crop not found excepction
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getCropFertilizers(@PathVariable Long cropId)
      throws CropNotFoundExcepction {
    Crop crop = cropService.findById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();

    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();

    return ResponseEntity.ok(fertilizersDto);
  }
}
