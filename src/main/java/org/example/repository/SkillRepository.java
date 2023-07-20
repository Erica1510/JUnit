package org.example.repository;

import org.example.models.Skill;

import java.util.List;

public interface SkillRepository {
    void addSkill(Skill skill);

    void deleteSkill(Skill skill);

    Skill getSkill(int id);
    List<Skill> getAllSkills();
}
