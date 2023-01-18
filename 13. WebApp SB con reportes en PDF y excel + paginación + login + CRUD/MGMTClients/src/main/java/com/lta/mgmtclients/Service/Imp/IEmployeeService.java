package com.lta.mgmtclients.Service.Imp;

import com.lta.mgmtclients.Model.Employee;
import com.lta.mgmtclients.Repository.EmployeeRepository;
import com.lta.mgmtclients.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IEmployeeService implements EmployeeService {
    @Autowired
    private EmployeeRepository er;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return (List<Employee>) er.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> findAll(Pageable pageable) {
        return er.findAll(pageable);
    }

    @Override
    public void save(Employee employee) {
        er.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long idE) {
        return er.findById(idE).orElse(null);
    }

    @Override
    public void delete(Long idE) {
        er.deleteById(idE);
    }
}
