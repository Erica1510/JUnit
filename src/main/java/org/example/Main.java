package org.example;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;
import org.example.repository.HibernatePersonRepository;
import org.example.repository.HibernateSkillRepository;
import org.example.repository.HibernateSkillSetRepository;
import org.example.repository.PersonRepository;
import org.example.services.*;
import org.hibernate.Hibernate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        HibernatePersonRepository repository = new HibernatePersonRepository();
        System.out.println(repository.getPerson(1));
        PersonCsv personCsv = new PersonCsv();
        SkillCSV skillCsv = new SkillCSV();
        PersonService personService = new DefaultPersonService(personCsv, new HibernatePersonRepository());
        SkillService skillService = new DefaultSkillService(skillCsv, new HibernateSkillRepository());
        SkillSetService skillSetService = new DefaultSkillSetService(new HibernateSkillSetRepository());

        // Add persons
        personService.addPerson(new Person("John", "Doe", "john@example.com"));
        personService.addPerson(new Person("Jane", "Smith", "jane@example.com"));

        // Add skills
        skillService.addSkill(new Skill("Java", "Programming"));
        skillService.addSkill(new Skill("Python", "Programming"));

        // Add skill sets
        Person person1 = personService.getPerson(1);
        Skill skill1 = skillService.getSkill(1);
        Skill skill2 = skillService.getSkill(2);
        SkillSet skillSet1 = new SkillSet(person1, skill1, 3);
        SkillSet skillSet2 = new SkillSet(person1, skill2, 4);
        skillSetService.addSkillSet(skillSet1);
        skillSetService.addSkillSet(skillSet2);

        // Retrieve all skill sets for a person
        List<SkillSet> skillSets = skillSetService.getSkillSetsByPerson(person1);
        System.out.println("Skill Sets for Person with ID 1:");
        for (SkillSet skillSet : skillSets) {
            System.out.println(skillSet);
        }
        System.out.println();

        // Retrieve all persons with their skill sets
        List<Person> persons = personService.getAllPersonsWithSkillSets();
        System.out.println("All Persons with Skill Sets:");
        for (Person person : persons) {
            System.out.println(person);
            for (SkillSet skillSet : person.getSkillSets()) {
                System.out.println("\t" + skillSet);
            }
        }
        PersonRepository personRepository = new HibernatePersonRepository();
        Person person = personRepository.getPerson(1);

        System.out.println(person);
    }
}