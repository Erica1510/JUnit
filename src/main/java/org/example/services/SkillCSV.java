package org.example.services;

import org.example.models.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SkillCSV {
    public static void convertToCSV(List<Skill> skills, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("id,name,domain");
            writer.newLine();
            for (Skill skill : skills) {
                writer.write(skill.getId() + "," + skill.getName() + "," + skill.getDomain());
                writer.newLine();
            }
            System.out.println("CSV file created successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while creating CSV file: " + e.getMessage());
        }
    }

    public static List<Skill> readFromCSV(String filePath) {
        List<Skill> skills = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String domain = fields[2];
                Skill skill = new Skill(id, name, domain);
                skills.add(skill);
            }
            System.out.println("CSV file read successfully: " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while reading CSV file: " + e.getMessage());
        }
        return skills;
    }

    public static void writeToCSV(List<Skill> skills, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header row
            writer.write("id,name,domain");
            writer.newLine();

            // Write each skill as a separate row
            for (Skill skill : skills) {
                writer.write(skill.getId() + "," + skill.getName() + "," + skill.getDomain());
                writer.newLine();
            }

            System.out.println("CSV file written successfully: " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while writing CSV file: " + e.getMessage());
        }
    }
}
