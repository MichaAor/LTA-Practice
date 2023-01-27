package com.lta.apilibrarybid.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Corresponde al Ejemplo MANY TO ONE / ONE TO MANY
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;
/**
* mappedBy para la entidad que no sera propietaria
 * NOTA REF VITAL: Si usa lombook, jamas usar @DATA si se implementa SET para collection de Collection
*/

    @OneToMany(mappedBy = "library",cascade = CascadeType.ALL)
    Set<Book> books = new HashSet<>();
}
