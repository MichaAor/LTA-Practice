package com.lta.crudstudents.Service;

import com.lta.crudstudents.Model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
     List<Student> GetAll();
     Optional<Student> Get(Long id);
     void Save(Student st);
     void Update(Student st);
     void Delete(Long id);
}
