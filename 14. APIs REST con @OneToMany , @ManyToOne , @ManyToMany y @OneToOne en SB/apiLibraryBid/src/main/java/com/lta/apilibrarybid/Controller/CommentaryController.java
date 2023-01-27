package com.lta.apilibrarybid.Controller;

import com.lta.apilibrarybid.Exceptions.ResourceNotFoundException;
import com.lta.apilibrarybid.Model.Commentary;
import com.lta.apilibrarybid.Repository.CommentaryRepository;
import com.lta.apilibrarybid.Repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentaryController {
    @Autowired
    private CommentaryRepository cr;

    @Autowired
    private PublicationRepository pr;

    @GetMapping("/api/publications/{idP}/commentaries")
    public ResponseEntity<Page<Commentary>> getCommentariesPublication(@PathVariable Long idP
                                                                       , Pageable pageable){
        return new ResponseEntity<>(cr.findAllByPublicationId(idP,pageable), HttpStatus.OK);
    }

    @PostMapping("/api/publications/{idP}/commentaries")
    public ResponseEntity<Commentary> save(@PathVariable Long idP
                                          ,@Valid @RequestBody Commentary commentary){
        return pr.findById(idP).map(publication -> {
            commentary.setPublication(publication);
            return ResponseEntity.ok(cr.save(commentary));
        }).orElseThrow(()->new ResourceNotFoundException("Publication not found with ID:"+idP));
    }

    @PutMapping("/api/publications/{idP}/commentaries/{idC}")
    public ResponseEntity<Commentary> update(@PathVariable Long idP
            ,@Valid @RequestBody Commentary commReq,@PathVariable Long idC) {
        if(!pr.existsById(idP)){
            throw new ResourceNotFoundException("Publication not found with ID:"+idP);
        }
        return cr.findById(idC).map(commentary -> {
            commentary.setText(commReq.getText());
            return ResponseEntity.ok(cr.save(commentary));
        }).orElseThrow(()->new ResourceNotFoundException("Commentary not found with ID:"+idC));
    }

    @DeleteMapping("/api/publications/{idP}/commentaries/{idC}")
    public ResponseEntity<?> delete(@PathVariable Long idP,@PathVariable Long idC){
        return cr.findByIdAndPublicationId(idC,idP).map(commentary -> {
            cr.deleteById(idC);
            return ResponseEntity.ok().build();
        })
        .orElseThrow(
         ()->new ResourceNotFoundException("No comment with id"+ idC +"or post with id found" + idP));
    }
}
