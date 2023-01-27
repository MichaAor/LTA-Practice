package com.lta.apilibrarybid.Controller;

import com.lta.apilibrarybid.Model.Party;
import com.lta.apilibrarybid.Model.Person;
import com.lta.apilibrarybid.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonRepository pr;

    @GetMapping
    public ResponseEntity<Collection<Person>> getAll(){
        return new ResponseEntity<>(pr.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idP}")
    public ResponseEntity<Person> get(@PathVariable Long idP){
        Person person = pr.findById(idP).orElseThrow();
        if(person != null){
            return new ResponseEntity<>(person,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Person> save(@Valid @RequestBody Person person){
        return new ResponseEntity<>(pr.save(person),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idP}")
    public ResponseEntity<?> delete(@PathVariable Long idP){
        pr.deleteById(idP);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{idP}/parties")
    public ResponseEntity<Collection<Party>> getPartiesPerson(@PathVariable Long idP){
        Person person = pr.findById(idP).orElseThrow();

        if(person != null){
            return new ResponseEntity<>(person.getParties(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
