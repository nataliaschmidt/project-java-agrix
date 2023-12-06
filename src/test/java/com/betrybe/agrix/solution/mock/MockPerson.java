package com.betrybe.agrix.solution.mock;

import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person mock.
 */
public class MockPerson {

  /**
   * Gets mock person.
   *
   * @return the mock person
   */
  public static Person getMockPerson() {
    Person person = new Person();
    person.setId(1L);
    person.setUsername("personteste");
    person.setPassword("123456");
    person.setRole(Role.USER);

    return person;
  }

}
