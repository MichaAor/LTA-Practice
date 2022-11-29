package com.example.crudemployees.Service;

import com.example.crudemployees.Model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> GetAll();
    Optional<Employee> Get(Long idE);
    void Save(Employee emp);
    void Update(Employee emp);
    void Delete(Long idE);
}
