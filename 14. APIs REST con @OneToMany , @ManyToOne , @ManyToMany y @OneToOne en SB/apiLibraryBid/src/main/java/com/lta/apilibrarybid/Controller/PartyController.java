package com.lta.apilibrarybid.Controller;

import com.lta.apilibrarybid.Model.Party;
import com.lta.apilibrarybid.Model.Person;
import com.lta.apilibrarybid.Repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/parties")
public class PartyController {
    @Autowired
    private PartyRepository pr;

    @GetMapping
    public ResponseEntity<Collection<Party>> getAll(){
        return new ResponseEntity<>(pr.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{idP}")
    public ResponseEntity<Party> get(@PathVariable Long idP){
        Party party = pr.findById(idP).orElseThrow();
        if(party != null){
            return new ResponseEntity<>(party,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Party person){
        return new ResponseEntity<>(pr.save(person),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idP}")
    public ResponseEntity<?> delete(@PathVariable Long idP){
        pr.deleteById(idP);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{idP}/parties")
    public ResponseEntity<Collection<Person>> getPersonsParty(@PathVariable Long idP){
        Party party = pr.findById(idP).orElseThrow();

        if(party != null){
            return new ResponseEntity<>(party.getPersons(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
