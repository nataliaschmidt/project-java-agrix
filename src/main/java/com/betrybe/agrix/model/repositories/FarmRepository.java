package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Farm repository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {}
