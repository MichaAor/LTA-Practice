package com.lta.apilibrarybid.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Corresponde al Ejemplo MANY TO MANY & ONE TO MANY
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;
}
