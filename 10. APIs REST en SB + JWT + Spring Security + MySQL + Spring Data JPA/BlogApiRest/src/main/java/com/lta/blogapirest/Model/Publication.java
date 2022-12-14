package com.lta.blogapirest.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "publication",cascade = CascadeType.ALL,orphanRemoval = true) //orphanRemoval = true elimina
    private Set<Commentary> commentaries;                                               //todo el contenido mas sus asociados

    public Publication(String title,String description,String content){
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
