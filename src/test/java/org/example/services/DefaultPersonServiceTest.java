package org.example.services;

import org.example.models.Person;

import org.example.repository.HibernatePersonRepository;
import org.example.repository.PersonRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultPersonServiceTest {
    private PersonCsv personCsv;
    private PersonRepository personRepository;
    private DefaultPersonService personService;

    @BeforeEach
    public void setUp() {
        // Set the test system property before initializing the repository
        System.setProperty("test", "true");

        personCsv = new PersonCsv();
        personRepository = new HibernatePersonRepository(); // Use a custom test implementation of PersonRepository
        personService = new DefaultPersonService(personCsv, personRepository);
    }
    @Test
    public void testAddPerson() {
        Person person = new Person(1, "John", "Doe", "john@example.com");
        personService.addPerson(person);

        List<Person> persons = personRepository.getAllPersons();
        assertEquals(1, persons.size());
        assertEquals(person, persons.get(0));
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person(1, "John", "Doe", "john@example.com");
        personRepository.addPerson(person);

        personService.deletePerson(person);

        List<Person> persons = personRepository.getAllPersons();
        assertEquals(0, persons.size());
    }

    @Test
    public void testGetPerson() {
        Person expectedPerson = new Person(1, "John", "Doe", "john@example.com");
        personRepository.addPerson(expectedPerson);

        Person actualPerson = personService.getPerson(1);

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void testImportPersonsFromCSV() {
        String filePath = "C:\\Users\\crme038\\Moodle\\Junit_ref\\src\\main\\resources\\persons.csv";
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person(1, "John", "Doe", "john@example.com"));
        expectedPersons.add(new Person(2, "Jane", "Smith", "jane@example.com"));

        personCsv.convertToCSV(expectedPersons, filePath); // Create the CSV file for testing

        // Set the test system property before importing persons from CSV
        System.setProperty("test", "true");
        personService.importPersonsFromCSV(filePath);

        List<Person> actualPersons = personRepository.getAllPersons();
        assertEquals(expectedPersons.size(), actualPersons.size());
        assertTrue(actualPersons.containsAll(expectedPersons));
    }

    @Test
    public void testExportPersonsToCSV() {
        String filePath = "C:\\Users\\crme038\\Moodle\\Junit_ref\\src\\main\\resources\\persons.csv";
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "John", "Doe", "john@example.com"));
        persons.add(new Person(2, "Jane", "Smith", "jane@example.com"));
        personRepository.addPerson(persons.get(0));
        personRepository.addPerson(persons.get(1));

        personService.exportPersonsToCSV(filePath);

        List<Person> exportedPersons = personCsv.readFromCSV(filePath);
        assertEquals(persons.size(), exportedPersons.size());
        assertTrue(exportedPersons.containsAll(persons));
    }

    @AfterEach
    public void tearDown() {
        // Close the session factory after each test
        if (HibernatePersonRepository.getFactory() != null) {
            HibernatePersonRepository.getFactory().close();
        }
    }
}
