package com.betrybe.agrix.service.exceptions;

/**
 * The type Crop not found excepction.
 */
public class CropNotFoundExcepction extends RuntimeException {

  /**
   * Instantiates a new Crop not found excepction.
   */
  public CropNotFoundExcepction() {
    super("Plantação não encontrada!!");
  }
}
