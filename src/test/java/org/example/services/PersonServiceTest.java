package org.example.services;

import org.example.models.Person;
import org.example.repository.HibernatePersonRepository;
import org.example.repository.PersonRepository;
import org.example.services.DefaultPersonService;
import org.example.services.PersonCsv;
import org.example.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceTest {
    private PersonService personService;

    @BeforeEach
    void setUp() {
        PersonCsv personCsv = new PersonCsv();
        PersonRepository personRepository = new HibernatePersonRepository();
        personService = new DefaultPersonService(personCsv, personRepository);
    }

    @Test
    void testAddPerson() {
        Person person = new Person("John", "Doe", "john@example.com");
        personService.addPerson(person);
        Person retrievedPerson = personService.getPerson(person.getId());
        assertEquals(person.getName(), retrievedPerson.getName());
        assertEquals(person.getSurname(), retrievedPerson.getSurname());
        assertEquals(person.getEmail(), retrievedPerson.getEmail());
    }


}