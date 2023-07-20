package org.example.services;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;

import org.example.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

public class DefaultPersonService implements PersonService{
    private final PersonCsv personCsv;
    private final PersonRepository personRepository;

    public DefaultPersonService(PersonCsv personCsv, PersonRepository personRepository) {
        this.personCsv = personCsv;
        this.personRepository = personRepository;
    }

    @Override
    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.deletePerson(person);
    }

    @Override
    public Person getPerson(int id) {
        return personRepository.getPerson(id);
    }

    @Override
    public void importPersonsFromCSV(String filePath) {
        List<Person> persons = personCsv.readFromCSV(filePath);
        for (Person person : persons) {
            // Add validation and exception handling logic here if needed
            personRepository.addPerson(person);
        }
    }

    @Override
    public void exportPersonsToCSV(String filePath) {
        List<Person> persons = personRepository.getAllPersons();
        personCsv.convertToCSV(persons, filePath);
    }
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public void addSkillSetToPerson(Person person, Skill skill, int level) {
        SkillSet skillSet = new SkillSet(person, skill, level);
        person.getSkillSets().add(skillSet);
    }

    public void removeSkillSetFromPerson(Person person, SkillSet skillSet) {
        person.getSkillSets().remove(skillSet);
    }

    public List<SkillSet> getSkillSetsByPerson(Person person) {
        return person.getSkillSets();
    }
    public List<Person> getAllPersonsWithSkillSets() {
        return personRepository.getAllPersonsWithSkillSets();
    }
}