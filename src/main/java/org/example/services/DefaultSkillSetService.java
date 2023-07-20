package org.example.services;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;
import org.example.repository.SkillSetRepository;


import java.util.List;

public class DefaultSkillSetService implements SkillSetService {
    private SkillSetRepository skillSetRepository;

    public DefaultSkillSetService(SkillSetRepository skillSetRepository) {
        this.skillSetRepository = skillSetRepository;
    }

    @Override
    public void addSkillSet(SkillSet skillSet) {
        skillSetRepository.addSkillSet(skillSet);
    }

    @Override
    public void deleteSkillSet(SkillSet skillSet) {
        skillSetRepository.deleteSkillSet(skillSet);
    }

    @Override
    public SkillSet getSkillSet(int id) {
        return skillSetRepository.getSkillSet(id);
    }

    @Override
    public List<SkillSet> getAllSkillSets() {
        return skillSetRepository.getAllSkillSets();
    }

    @Override
    public List<SkillSet> getSkillSetsByPerson(Person person) {
        return skillSetRepository.getSkillSetsByPerson(person);
    }

    @Override
    public List<SkillSet> getSkillSetsBySkill(Skill skill) {
        return skillSetRepository.getSkillSetsBySkill(skill);
    }
}
