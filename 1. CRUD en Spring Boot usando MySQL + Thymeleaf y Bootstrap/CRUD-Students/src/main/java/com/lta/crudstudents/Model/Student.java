package com.lta.crudstudents.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name",nullable = false,length = 30)
    private String name;

    @Column(name = "Lastname",nullable = false,length = 30)
    private String lastname;

    @Column(name = "Email",nullable = false,length = 80,unique = true)
    private String email;


    public Student(String name,String lastname,String email){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }



}
