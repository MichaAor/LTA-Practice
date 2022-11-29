package com.example.crudemployees.Service;

import com.example.crudemployees.Model.Employee;
import com.example.crudemployees.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository er;

    @Override
    public List<Employee> GetAll() {
        return er.findAll();
    }

    @Override
    public Optional<Employee> Get(Long idE) {
        return er.findById(idE);
    }

    @Override
    public void Save(Employee emp) {
        er.save(emp);
    }

    @Override
    public void Update(Employee emp) {
        er.save(emp);
    }

    @Override
    public void Delete(Long idE) {
        er.deleteById(idE);
    }
}
