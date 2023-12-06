package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.controller.dto.PersonDtoWithoutPassword;
import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create response entity.
   *
   * @param person the person
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<PersonDtoWithoutPassword> create(@RequestBody PersonDto person) {
    Person savedPerson = personService.create(person.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(PersonDtoWithoutPassword.fromEntity(savedPerson));
  }
}
