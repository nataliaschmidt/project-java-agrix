package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FarmRepository;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired

  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {

    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Create farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Find by id farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Create crop crop.
   *
   * @param crop   the crop
   * @param farmId the farm id
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop createFarmCrop(Crop crop, Long farmId) throws FarmNotFoundException {
    Farm farm = findById(farmId);

    crop.setFarm(farm);

    return cropRepository.save(crop);
  }

  /**
   * Gets farm crop.
   *
   * @param farmId the farm id
   * @return the farm crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> getFarmCrop(Long farmId) throws FarmNotFoundException {
    Farm farm = findById(farmId);

    return farm.getCrops();
  }
}
