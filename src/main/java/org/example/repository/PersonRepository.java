package org.example.repository;

import org.example.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonRepository {
    void addPerson(Person person);

    void deletePerson(Person person);

    Person getPerson(int id);
    List<Person> getAllPersons();
    List<Person> getAllPersonsWithSkillSets();
}
