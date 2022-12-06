package com.lta.filmsapp.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFilm")
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String overview;
    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate releaseD;
    @NotBlank
    private String ytVID;
    private String coverUrl;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY) //Realiza la carga solo cuando se necesita
    @JoinTable(name = "filmGenre"
            ,joinColumns = @JoinColumn(name = "idFilm")
            ,inverseJoinColumns = @JoinColumn(name = "idGenre"))
    private List<Genre> genres = new ArrayList<>();

    @Transient //No se guarda en la bdd,es temporal
    private MultipartFile cover;
}


