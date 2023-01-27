package com.lta.apilibrarybid.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Corresponde al Ejemplo MANY TO ONE / ONE TO MANY
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "library_ID")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Library library;
}
