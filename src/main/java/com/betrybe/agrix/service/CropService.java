package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.service.exceptions.CropNotFoundExcepction;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerService fertilizerService) {

    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Find by id crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundExcepction the crop not found excepction
   */
  public Crop findById(Long id) throws CropNotFoundExcepction {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundExcepction::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Gets crops to harvest date interval.
   *
   * @param start the start
   * @param end   the end
   * @return the crops to harvest date interval
   * @throws CropNotFoundExcepction the crop not found excepction
   */
  public List<Crop> getCropsToHarvestDateInterval(LocalDate start, LocalDate end)
      throws CropNotFoundExcepction {
    return cropRepository.findByHarvestDateBetween(start, end)
        .orElseThrow(CropNotFoundExcepction::new);
  }

  /**
   * Associate crop and fertilizer string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFoundExcepction      the crop not found excepction
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */

  @Transactional
  public String associateCropAndFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundExcepction, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    if (crop.getFertilizers().contains(fertilizer)) {
      return "Esta associação já existe.";
    }

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);

    return "Fertilizante e plantação associados com sucesso!";

  }
}
