package com.lta.apilibrarybid.Controller;

import com.lta.apilibrarybid.Model.Book;
import com.lta.apilibrarybid.Model.Library;
import com.lta.apilibrarybid.Repository.BookRepository;
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
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository br;

    @Autowired
    private LibraryRepository lr;

    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book){
        Optional<Library> libOp = lr.findById(book.getLibrary().getId());
        if(libOp.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        book.setLibrary(libOp.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(br.save(book));
    }

    @PutMapping("/{idB}")
    public ResponseEntity<Book> editBook(@Valid @RequestBody Book book
                                              ,@PathVariable("idB")Long idB){
        Optional<Book> oBook = br.findById(idB);
        Optional<Library> oLib = lr.findById(oBook.get().getLibrary().getId());
        if(oBook.isEmpty() || oLib.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        oBook.get().setId(idB);
        oBook.get().setName(book.getName());
        oBook.get().setLibrary(oLib.get());
        br.save(oBook.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idB}")
    public ResponseEntity<?> deleteLibrary(@PathVariable Long idB){
        if(br.existsById(idB)){
            br.deleteById(idB);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idB}")
    public ResponseEntity<Book> getLibrary(@PathVariable Long idB){
        if(br.existsById(idB)){
            return ResponseEntity.ok().body(br.findById(idB).get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getLibraries(Pageable pageable){
        return ResponseEntity.ok(br.findAll(pageable));
    }
}
