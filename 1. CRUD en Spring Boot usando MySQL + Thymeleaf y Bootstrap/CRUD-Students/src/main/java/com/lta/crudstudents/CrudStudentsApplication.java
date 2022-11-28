package com.lta.crudstudents;

import com.lta.crudstudents.Model.Student;
import com.lta.crudstudents.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudStudentsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudStudentsApplication.class, args);
    }

    @Autowired
    private StudentRepository sr;

    @Override
    public void run(String... args) throws Exception {
/*
        Student st1 = new Student("Misael","Flores","misaelflores4190@gmail.com");
        Student st2 = new Student("Micha","Flor","bluckiz@gmail.com");
        sr.save(st1);
        sr.save(st2);
*/
    }
}
