package com.lta.mgmtclients.Service;

import com.lta.mgmtclients.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Page<Employee> findAll(Pageable pageable);
    void save(Employee employee);
    Employee findById(Long idE);
    void delete(Long idE);
}
