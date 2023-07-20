package org.example.services;
import org.example.models.Skill;
import org.example.repository.HibernateSkillRepository;
import org.example.repository.SkillRepository;

import java.util.List;

public class DefaultSkillService implements SkillService{
    private final SkillCSV skillCsv;
    private final SkillRepository skillRepository;

    public DefaultSkillService(SkillCSV skillCsv, SkillRepository skillRepository) {
        this.skillCsv = skillCsv;
        this.skillRepository = skillRepository;
    }

    @Override
    public void addSkill(Skill skill) {
        skillRepository.addSkill(skill);
    }

    @Override
    public void deleteSkill(Skill skill) {
        skillRepository.deleteSkill(skill);
    }

    @Override
    public Skill getSkill(int id) {
        return skillRepository.getSkill(id);
    }

    @Override
    public void importSkillsFromCSV(String filePath) {
        List<Skill> skills = skillCsv.readFromCSV(filePath);
        for (Skill skill : skills) {
            // Add validation and exception handling logic here if needed
            skillRepository.addSkill(skill);
        }
    }

    @Override
    public void exportSkillsToCSV(String filePath) {
        List<Skill> skills = skillRepository.getAllSkills();
        skillCsv.convertToCSV(skills, filePath);
    }
    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.getAllSkills();
    }
}