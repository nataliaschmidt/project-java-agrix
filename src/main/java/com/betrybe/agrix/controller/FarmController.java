package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param farm the farm
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farm) {
    Farm savedFarm = farmService.createFarm(farm.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(savedFarm));
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> findById(@PathVariable("id") Long id)
      throws FarmNotFoundException {
    Farm farm = farmService.findById(id);
    return ResponseEntity.ok(FarmDto.fromEntity(farm));
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> findAll() {
    List<Farm> allFarms = farmService.findAll();
    List<FarmDto> data = allFarms.stream()
        .map(FarmDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Create crop response entity.
   *
   * @param crop the crop
   * @param id   the id
   * @return the response entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<CropDto> createCrop(@RequestBody CropDto crop, @PathVariable("id") Long id)
      throws FarmNotFoundException {
    Crop savedCrop = farmService.createFarmCrop(crop.toEntity(), id);
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(savedCrop));
  }

  /**
   * Gets farm crop.
   *
   * @param id the id
   * @return the farm crop
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<List<CropDto>> getFarmCrop(@PathVariable("id") Long id)
      throws FarmNotFoundException {
    List<Crop> crops = farmService.getFarmCrop(id);
    List<CropDto> data = crops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);

  }

}
