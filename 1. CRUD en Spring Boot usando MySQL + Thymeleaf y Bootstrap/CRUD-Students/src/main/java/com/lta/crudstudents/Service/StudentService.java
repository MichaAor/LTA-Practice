package com.lta.crudstudents.Service;

import com.lta.crudstudents.Model.Student;
import com.lta.crudstudents.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepository sr;

    @Override
    public List<Student> GetAll(){
        return sr.findAll();
    }

    @Override
    public Optional<Student> Get(Long id){
        return sr.findById(id);
    }

    @Override
    public void Save(Student st){
        sr.save(st);
    }

    @Override
    public void Update(Student st) {
        sr.save(st);
    }

    @Override
    public void Delete(Long id){
        sr.deleteById(id);
    }
}
