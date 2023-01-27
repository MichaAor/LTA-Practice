package com.lta.apilibrarybid.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Corresponde al Ejemplo MANY TO MANY & ONE TO MANY
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "person_party"
            ,joinColumns = @JoinColumn(name = "person_id",referencedColumnName = "person_id")
            ,inverseJoinColumns = @JoinColumn(name = "party_id",referencedColumnName = "party_id")
    )
    private Set<Party> parties = new HashSet<>();
}
