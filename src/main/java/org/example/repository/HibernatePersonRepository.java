package org.example.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Person;
import org.example.models.Skill;
import org.example.models.SkillSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HibernatePersonRepository implements PersonRepository{
    private Session session;
    private static  SessionFactory factory;

    private void createSession() {
        if (factory == null) {
            boolean isTest = System.getProperty("test") != null && System.getProperty("test").equals("true");

            Configuration configuration = new Configuration();
            factory = new Configuration()
                    .configure(isTest?"hibernate.cfg.test.xml":"hibernate.cfg.xml")
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Skill.class)
                    .addAnnotatedClass(SkillSet.class)
                    .buildSessionFactory();
        }
        session = factory.getCurrentSession();
    }

    @Override
    public void addPerson(Person person) {
        createSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
    }

    @Override
    public void deletePerson(Person person) {
        createSession();
        session.beginTransaction();
        session.delete(person);
        session.getTransaction().commit();
    }

    @Override
    public Person getPerson(int id) {
        createSession();
        session.beginTransaction();
        Person person = session.createQuery("SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.skillSets WHERE p.id = :id", Person.class)
                .setParameter("id", id)
                .uniqueResult();
        session.getTransaction().commit();
        return person;
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    @Override
    public List<Person> getAllPersons() {
        createSession();
        session.beginTransaction();
        List<Person> persons = session.createQuery("FROM Person", Person.class).getResultList();
        session.getTransaction().commit();
        return persons;
    }
    @Override
    public List<Person> getAllPersonsWithSkillSets() {
        createSession();
        session.beginTransaction();
        List<Person> persons = session.createQuery("FROM Person", Person.class).getResultList();

        return session.createQuery("SELECT DISTINCT p FROM Person p JOIN FETCH p.skillSets", Person.class).getResultList();

    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (factory != null) {
                factory.close();
            }
        }));
    }



}