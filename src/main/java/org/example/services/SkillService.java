package org.example.services;

import org.example.models.Skill;

import java.util.List;

public interface SkillService {
    void addSkill(Skill skill);

    void deleteSkill(Skill skill);

    Skill getSkill(int id);

    void importSkillsFromCSV(String filePath);

    void exportSkillsToCSV(String filePath);
    List<Skill> getAllSkills();
}
