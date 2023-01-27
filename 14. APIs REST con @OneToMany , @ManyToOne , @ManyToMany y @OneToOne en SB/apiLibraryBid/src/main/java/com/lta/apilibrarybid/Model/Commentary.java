package com.lta.apilibrarybid.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Corresponde al Ejemplo ONE TO ONE & MANY TO ONE
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Commentary extends Audit{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "publication_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore //Prohibe traer la publicacion cuando se recupera, algo extranio
    private Publication publication;
}
