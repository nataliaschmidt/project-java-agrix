package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person dto without password.
 */
public record PersonDtoWithoutPassword(Long id, String username, Role role) {

  /**
   * From entity person dto without password.
   *
   * @param person the person
   * @return the person dto without password
   */
  public static PersonDtoWithoutPassword fromEntity(Person person) {
    return new PersonDtoWithoutPassword(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }

}
