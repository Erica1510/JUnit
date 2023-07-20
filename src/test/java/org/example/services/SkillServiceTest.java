package org.example.services;

import org.example.models.Skill;
import org.example.repository.HibernateSkillRepository;
import org.example.repository.SkillRepository;
import org.example.services.DefaultSkillService;
import org.example.services.SkillCSV;
import org.example.services.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillServiceTest {
    private SkillService skillService;

    @BeforeEach
    void setUp() {
        SkillCSV skillCsv = new SkillCSV();
        SkillRepository skillRepository = new HibernateSkillRepository();
        skillService = new DefaultSkillService(skillCsv, skillRepository);
    }

    @Test
    void testAddSkill() {
        Skill skill = new Skill("Java", "Programming");
        skillService.addSkill(skill);
        Skill retrievedSkill = skillService.getSkill(skill.getId());
        assertEquals(skill.getName(), retrievedSkill.getName());
        assertEquals(skill.getDomain(), retrievedSkill.getDomain());
    }


}