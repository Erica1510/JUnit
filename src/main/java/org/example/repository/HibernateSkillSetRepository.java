package org.example.repository;

import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSkillSetRepository implements SkillSetRepository {
    private Session session;
    private SessionFactory factory;

    private void createSession() {
        Configuration configuration = new Configuration();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillSet.class)
                .addAnnotatedClass(Skill.class)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();
        session = factory.getCurrentSession();
    }
    private void openSession() {
        if (session == null || !session.isOpen()) {
            createSession();
        }
    }
    @Override
    public void addSkillSet(SkillSet skillSet) {
        createSession();
        session.beginTransaction();
        session.save(skillSet);
        session.getTransaction().commit();
    }

    @Override
    public void deleteSkillSet(SkillSet skillSet) {
        createSession();
        session.beginTransaction();
        session.delete(skillSet);
        session.getTransaction().commit();
    }

    @Override
    public SkillSet getSkillSet(int id) {
        createSession();
        session.beginTransaction();
        return session.get(SkillSet.class, id);
    }

    @Override
    public List<SkillSet> getAllSkillSets() {
        createSession();
        session.beginTransaction();
        return session.createQuery("FROM SkillSet", SkillSet.class).getResultList();
    }

    @Override
    public List<SkillSet> getSkillSetsByPerson(Person person) {
        createSession();
        session.beginTransaction();
        return session.createQuery("FROM SkillSet WHERE person = :person", SkillSet.class)
                .setParameter("person", person)
                .getResultList();
    }

    @Override
    public List<SkillSet> getSkillSetsBySkill(Skill skill) {
        createSession();
        session.beginTransaction();
        return session.createQuery("FROM SkillSet WHERE skill = :skill", SkillSet.class)
                .setParameter("skill", skill)
                .getResultList();
    }

}
