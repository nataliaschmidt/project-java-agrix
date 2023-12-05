package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.solution.mock.MockPerson;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

/**
 * The type Person service test.
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Testes da classe PersonService")
public class PersonServiceTest {

  /**
   * The Person service.
   */
  @Autowired
  PersonService personService;

  /**
   * The Person repository.
   */
  @MockBean
  PersonRepository personRepository;

  /**
   * Test get person by id.
   */
  @Test
  @DisplayName("Teste do método getPersonById")
  public void testGetPersonById () {
    Person personExpect = MockPerson.getMockPerson();
    Mockito.when(
        personRepository.findById(eq(1L))
    ).thenReturn(Optional.of(personExpect));

    Person findPerson = personService.getPersonById(1L);
    Mockito.verify(personRepository).findById(eq(1L));

    assertNotNull(findPerson.getId());
    assertEquals(findPerson.getId(), personExpect.getId());
    assertEquals(findPerson.getUsername(), personExpect.getUsername());
    assertEquals(findPerson.getPassword(), personExpect.getPassword());
  }

  /**
   * Test get person by id not found.
   */
  @Test
  @DisplayName("Teste do método getPersonById lançando exceção")
  public void testGetPersonByIdNotFound () {
     Mockito.when(
        personRepository.findById(any())
    ).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(300L));

    Mockito.verify(personRepository).findById(eq(300L));
  }

  /**
   * Test get person by username.
   */
  @Test
  @DisplayName("Teste do método getPersonByUsername")
  public void testGetPersonByUsername () {
    Person personExpect = MockPerson.getMockPerson();

    Mockito.when(
        personRepository.findByUsername(eq("personteste"))
    ).thenReturn(Optional.of(personExpect));


    Person findPerson = personService.getPersonByUsername("personteste");
    Mockito.verify(personRepository).findByUsername(eq("personteste"));

    assertNotNull(findPerson.getId());
    assertEquals(findPerson.getId(), personExpect.getId());
    assertEquals(findPerson.getUsername(), personExpect.getUsername());
    assertEquals(findPerson.getPassword(), personExpect.getPassword());
  }

  /**
   * Test get person by username not found.
   */
  @Test
  @DisplayName("Teste do método getPersonByUsername lançando exceção")
  public void testGetPersonByUsernameNotFound () {
    Mockito.when(
        personRepository.findByUsername(any())
    ).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("personNotFound"));

    Mockito.verify(personRepository).findByUsername(eq("personNotFound"));
  }

  /**
   * Test create.
   */
  @Test
  @DisplayName("Teste do método create")
  public void testCreate () {

    Person personExpect = MockPerson.getMockPerson();

    Mockito.when(
        personRepository.save(any())
    ).thenReturn(personExpect);

    Person person = new Person();
    person.setUsername(personExpect.getUsername());
    person.setPassword(personExpect.getPassword());
    person.setRole(personExpect.getRole());

    Person personSaved = personService.create(person);

    assertNotNull(personSaved.getId());
    assertEquals(personExpect.getId(), personSaved.getId());
    assertEquals(personExpect.getUsername(), personSaved.getUsername());
    assertEquals(personExpect.getPassword(), personSaved.getPassword());
  }
}
