package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exceptions.CropNotFoundExcepction;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.util.List;
import org.apache.coyote.Response;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create response entity.
   *
   * @param fertilizer the fertilizer
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> create(@RequestBody FertilizerDto fertilizer) {
    Fertilizer savedFertilizer = fertilizerService.create(fertilizer.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FertilizerDto.fromEntity(savedFertilizer));
  }

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> findAll() {
    List<Fertilizer> allFertilizers = fertilizerService.findAll();
    List<FertilizerDto> data = allFertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<FertilizerDto> findById(@PathVariable("fertilizerId") Long id)
      throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.findById(id);
    return ResponseEntity.ok(FertilizerDto.fromEntity(fertilizer));
  }
}
