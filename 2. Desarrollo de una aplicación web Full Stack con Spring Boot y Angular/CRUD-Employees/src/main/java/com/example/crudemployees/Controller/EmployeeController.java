package com.example.crudemployees.Controller;

import com.example.crudemployees.Model.Employee;
import com.example.crudemployees.Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    @Autowired
    private IEmployeeService es;


    @GetMapping
    public List<Employee> GetAll(){
        return es.GetAll();
    }

    @GetMapping("/{idE}")
    public ResponseEntity<Employee> Get(@PathVariable("idE") Long idE){
        return ResponseEntity.ok(es.Get(idE).get());
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
