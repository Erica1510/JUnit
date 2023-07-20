package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name ="name")
    private String name;
    @Column(name = "domain")
    private String domain;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillSet> skillSets;

    public Skill(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

    public Skill(int id, String name, String domain) {
        this.id=id;
        this.name = name;
        this.domain = domain;
    }
}
