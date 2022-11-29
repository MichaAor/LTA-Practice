package com.example.crudemployees.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 30,nullable = false)
    private String name;
    @Column(name = "lastname",length = 30,nullable = false)
    private String lastname;
    @Column(name = "email",length = 60,nullable = false,unique = true)
    private String email;
}
