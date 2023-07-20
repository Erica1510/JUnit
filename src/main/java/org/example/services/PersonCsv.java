package org.example.services;

import org.example.models.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonCsv {
    public static void convertToCSV(List<Person> persons, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.append("id,name,surname,email");
            writer.append("\n");

            // Write CSV data
            int id = 1; // Start with id = 1
            for (Person person : persons) {
                person.setId(id++); // Assign the id value to the person object
                writer.append(String.valueOf(person.getId()));
                writer.append(",");
                writer.append(person.getName());
                writer.append(",");
                writer.append(person.getSurname());
                writer.append(",");
                writer.append(person.getEmail());
                writer.append("\n");
            }

            System.out.println("CSV file created successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while creating CSV file: " + e.getMessage());
        }
    }

    public static List<Person> readFromCSV(String filePath) {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header row
                }

                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String surname = fields[2];
                String email = fields[3];

                Person person = new Person(id, name, surname, email);
                persons.add(person);
            }

            System.out.println("CSV file read successfully: " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while reading CSV file: " + e.getMessage());
        }

        return persons;
    }
}
