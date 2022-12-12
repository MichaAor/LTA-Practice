package com.lta.inventoryrest.Controller;

import com.lta.inventoryrest.Model.Product;
import com.lta.inventoryrest.Service.Interface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService ips;

    @GetMapping
    public ResponseEntity<?> GetAll(){
        List<Product> prL = ips.GetAll();
        if(!prL.isEmpty()){
            return ResponseEntity.ok(prL);
        }
    return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idP}")
    public ResponseEntity<?> Get(@PathVariable("idP") Long idP){
        Optional<Product> pr = ips.Get(idP);
        if(pr.isPresent()){
            return ResponseEntity.ok(pr);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> Save(@RequestBody Product product){
            ips.Save(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{idP}")
    public ResponseEntity<?> Update(@RequestBody Product product,@PathVariable("idP") Long idP){
        product.setId(idP);
        ips.Update(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idP}")
    public ResponseEntity<?> Delete(@PathVariable("idP") Long idP){
        ips.Delete(idP);
        return ResponseEntity.ok().build();
    }


}
