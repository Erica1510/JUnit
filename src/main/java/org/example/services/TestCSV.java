package org.example.services;

import org.example.models.Person;
import org.example.models.Skill;

import java.util.ArrayList;
import java.util.List;

public class TestCSV {
    public static void main(String[] args) {
        String path = "src/main/resources/persons.csv";
        List<Person> list = new ArrayList<>();
        list.add(new Person("John", "Doe", "john@example.com"));
        list.add(new Person("Jane", "Smith", "jane@example.com"));
        list.add(new Person("Michael", "Johnson", "michael@example.com"));
        list.add(new Person("Emily", "Williams", "emily@example.com"));
        list.add(new Person("David", "Brown", "david@example.com"));
        PersonCsv.convertToCSV(list, path);

        String filePath = "src/main/resources/skills.csv";
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(1, "Java", "Programming"));
        skills.add(new Skill(2, "Python", "Programming"));
        skills.add(new Skill(3, "Data Analysis", "Analytics"));

        SkillCSV.writeToCSV(skills, filePath);

    }
}