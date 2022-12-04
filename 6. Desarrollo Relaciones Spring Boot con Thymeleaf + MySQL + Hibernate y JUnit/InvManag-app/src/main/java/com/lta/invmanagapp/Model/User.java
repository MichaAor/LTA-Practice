package com.lta.invmanagapp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45,nullable = false,unique = true)
    private String email;
    @Column(length = 10,nullable = false)
    private String password;
    //(Cascade:PERSIST )Permite persistencia del obj en si y sus obj relacionados
    //(Fetch: EAGER) Listar objetos relacionados
    //A LA HORA DE BORRAR SE QUITA EL CASCADE Y FETCH XK NO PERMITE EL BORRADO.
    @ManyToMany//(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",              //que no existan en la bdd
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String email,String password){
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

}
