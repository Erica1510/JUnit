package org.example.services;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;

import java.util.List;

public interface SkillSetService {
    void addSkillSet(SkillSet skillSet);

    void deleteSkillSet(SkillSet skillSet);

    SkillSet getSkillSet(int id);

    List<SkillSet> getAllSkillSets();

    List<SkillSet> getSkillSetsByPerson(Person person);

    List<SkillSet> getSkillSetsBySkill(Skill skill);
}
