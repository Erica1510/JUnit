package org.example.models;

import lombok.*;
import org.example.repository.HibernatePersonRepository;
import org.example.repository.PersonRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "skillset",
//            joinColumns = @JoinColumn(name = "person_id"),
//            inverseJoinColumns = @JoinColumn(name = "skill_id")
//    )
//    private List<Skill> skills;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillSet> skillSets;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person(int id, String name, String surname, String email) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public List<SkillSet> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<SkillSet> skillSets) {
        this.skillSets = skillSets;
    }


}
