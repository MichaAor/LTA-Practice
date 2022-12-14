package com.lta.blogapirest.Controller;

import com.lta.blogapirest.DTO.CommentaryDTO;
import com.lta.blogapirest.Service.Interface.ICommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commentaries")
public class CommentaryController {
    @Autowired
    private ICommentaryService ics;

    @GetMapping("/{idC}/publication/{idP}")
    public ResponseEntity<?> Get(@PathVariable("idC")Long idC,@PathVariable("idP")Long idP){
        return ResponseEntity.ok(ics.Get(idC,idP));
    }

    @GetMapping("/publication/{idP}")
    public ResponseEntity<?> GetByPublication(@PathVariable("idP")Long idP){
        return ResponseEntity.ok(ics.GetByPublication(idP));
    }

    @PostMapping("/{idP}")
    public ResponseEntity<?> Save(@PathVariable("idP")Long idP, @RequestBody CommentaryDTO comDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(ics.Save(idP,comDTO));
    }

    @PutMapping("/{idC}/publication/{idP}")
    public ResponseEntity<?> Update(@PathVariable("idC")Long idC,@PathVariable("idP")Long idP
                                    ,@RequestBody CommentaryDTO comDTO){
        return ResponseEntity.ok(ics.Update(idC,idP,comDTO));
    }

    @DeleteMapping("/{idC}/publication/{idP}")
    public ResponseEntity<?> Delete(@PathVariable("idC")Long idC,@PathVariable("idP")Long idP){
        ics.Delete(idC,idP);
        return ResponseEntity.ok().build();
    }


}
