package com.lta.apilibrarybid.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;
    private String location;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date date;

    @ManyToMany
    @JoinTable(name = "person_party"
            ,joinColumns = @JoinColumn(name = "party_id",referencedColumnName = "party_id")
            ,inverseJoinColumns = @JoinColumn(name = "person_id",referencedColumnName = "person_id")
    )
    private Set<Person> persons = new HashSet<>();
}
