package com.lta.apilibrarybid.Controller;

import com.lta.apilibrarybid.Exceptions.ResourceNotFoundException;
import com.lta.apilibrarybid.Model.Publication;
import com.lta.apilibrarybid.Repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {
    @Autowired
    private PublicationRepository pr;

    @GetMapping
    public ResponseEntity<Page<Publication>> getAll(Pageable pageable){
        return new ResponseEntity<>(pr.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idP}")
    public ResponseEntity<Publication> get(@PathVariable Long idP){

        Publication publication = pr.findById(idP).orElseThrow();
        if(publication != null){
            return new ResponseEntity<>(publication,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Publication> save(@Valid @RequestBody Publication publication){
        return new ResponseEntity<>(pr.save(publication),HttpStatus.CREATED);
    }

    @PutMapping("/{idP}")
    public ResponseEntity<Publication> update(@PathVariable Long idP
                                             ,@Valid @RequestBody Publication pubR){
        return pr.findById(idP).map(publication -> {
            publication.setTitle(pubR.getTitle());
            publication.setDescription(pubR.getDescription());
            publication.setContent(pubR.getContent());
            return ResponseEntity.ok(pr.save(publication));
        }).orElseThrow(()->new ResourceNotFoundException("Publication not found with ID: "+ idP));
    }

    @DeleteMapping("/{idP}")
    public ResponseEntity<?> delete(@PathVariable Long idP){
        return pr.findById(idP).map(publication -> {
            pr.deleteById(idP);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Publication not found with ID: "+ idP));
    }
}
