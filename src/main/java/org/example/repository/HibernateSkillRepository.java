package org.example.repository;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSkillRepository implements SkillRepository {
    private Session session;
    private SessionFactory factory;


    private void createSession() {
        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");?
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Skill.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(SkillSet.class)
                .buildSessionFactory();
        session = factory.getCurrentSession();
    }

    @Override
    public void addSkill(Skill skill) {
        createSession();
        session.beginTransaction();
        session.save(skill);
        session.getTransaction().commit();
    }

    @Override
    public void deleteSkill(Skill skill) {
        createSession();
        session.beginTransaction();
        session.delete(skill);
        session.getTransaction().commit();
    }

    @Override
    public Skill getSkill(int id) {
        createSession();
        session.beginTransaction();
        Skill skill = session.get(Skill.class, id);
        session.getTransaction().commit();
        return skill;
    }

    @Override
    public List<Skill> getAllSkills() {
        createSession();
        session.beginTransaction();
        List<Skill> skills = session.createQuery("FROM Skill", Skill.class).getResultList();
        session.getTransaction().commit();
        return skills;
    }
}

