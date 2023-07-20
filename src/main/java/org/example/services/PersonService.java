package org.example.services;

import org.example.models.Person;
import org.example.models.SkillSet;

import java.util.List;

public interface PersonService {
    void addPerson(Person person);

    void deletePerson(Person person);

    Person getPerson(int id);

    void importPersonsFromCSV(String filePath);

    void exportPersonsToCSV(String filePath);
    List<SkillSet> getSkillSetsByPerson(Person person);

    List<Person> getAllPersonsWithSkillSets();
}
