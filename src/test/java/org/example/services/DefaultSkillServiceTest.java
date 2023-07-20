package org.example.services;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.repository.HibernateSkillRepository;
import org.example.repository.PersonRepository;
import org.example.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSkillServiceTest {
    private  SkillCSV skillCsv;
    private  SkillRepository skillRepository;
    private DefaultSkillService skillService;

    @BeforeEach
    void setUp() {

         skillCsv = new SkillCSV();
           skillRepository = new HibernateSkillRepository();
           skillService = new DefaultSkillService(skillCsv, skillRepository);

    }

    @Test
    public void testAddSkill() {
        Skill skill=new Skill(1,"Strip Plastic","Dance");
        skillService.addSkill(skill);
        List<Skill> skills=skillRepository.getAllSkills();
        Skill retrievedSkill = skillService.getSkill(skill.getId());
        assertEquals(skill.getName(), retrievedSkill.getName());
        assertEquals(skill.getDomain(), retrievedSkill.getDomain());
    }
//@Test
//public void testAddSkill() {
//    Skill skill = new Skill(1, "Java", "Programming Language");
//    skillService.addSkill(skill);
//
//    List<Skill> skills = skillRepository.getAllSkills();,
//    assertEquals(1, skills.size());
//    assertEquals(skill, skills.get(0));
//}
    @Test
    public void testDeleteSkill(){
        Skill skill=new Skill(2,"Python","Programming");
        skillService.addSkill(skill);
        skillService.deleteSkill(skill);
        List<Skill> skills=skillRepository.getAllSkills();
        Skill retrievedSkill = skillService.getSkill(skill.getId());
        assertEquals(0, retrievedSkill.getName());
        assertEquals(0, retrievedSkill.getDomain());
    }

}