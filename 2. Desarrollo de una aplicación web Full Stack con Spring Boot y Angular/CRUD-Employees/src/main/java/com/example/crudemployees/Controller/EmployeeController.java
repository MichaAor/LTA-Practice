package com.example.crudemployees.Controller;

import com.example.crudemployees.Model.Employee;
import com.example.crudemployees.Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService es;


    @GetMapping
    public List<Employee> GetAll(){
        return es.GetAll();
    }

    @GetMapping("/{idE}")
    public Optional<Employee> Get(@PathVariable("idE") Long idE){
        return es.Get(idE);
    }

    @PostMapping
    public void Save(@RequestBody Employee emp){
        es.Save(emp);
    }

    @PutMapping("/{idE}")
    public void Update(@RequestBody Employee emp,@PathVariable("idE") Long idE){
        emp.setId(idE);
        es.Update(emp);
    }

    @DeleteMapping("{idE}")
    public void Delete(@PathVariable("idE") Long idE){
        es.Delete(idE);
    }
}
