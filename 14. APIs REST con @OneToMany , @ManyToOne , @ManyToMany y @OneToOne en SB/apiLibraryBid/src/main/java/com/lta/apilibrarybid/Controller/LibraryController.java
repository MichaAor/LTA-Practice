package com.lta.apilibrarybid.Controller;

import com.lta.apilibrarybid.Model.Library;
import com.lta.apilibrarybid.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {
    @Autowired
    private LibraryRepository lr;

    @PostMapping
    public ResponseEntity<Library> saveLibrary(@Valid @RequestBody Library library){
        return ResponseEntity.status(HttpStatus.CREATED).body(lr.save(library));
    }

    @PutMapping("/{idL}")
    public ResponseEntity<Library> editLibrary(@Valid @RequestBody Library library
                                              ,@PathVariable("idL")Long idL){
        Optional<Library> lib = lr.findById(idL);
        if(lib.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        lib.get().setId(idL);
        lib.get().setName(library.getName());
        lib.get().setBooks(library.getBooks());
        lr.save(lib.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idL}")
    public ResponseEntity<?> deleteLibrary(@PathVariable Long idL){
        if(lr.existsById(idL)){
            lr.deleteById(idL);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idL}")
    public ResponseEntity<Library> getLibrary(@PathVariable("idL") Long idL){
        if(lr.existsById(idL)){
            return ResponseEntity.ok().body(lr.findById(idL).get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<Library>> getLibraries(Pageable pageable){
        return ResponseEntity.ok(lr.findAll(pageable));
    }
}
