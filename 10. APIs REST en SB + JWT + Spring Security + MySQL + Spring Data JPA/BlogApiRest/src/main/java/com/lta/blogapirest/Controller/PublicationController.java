package com.lta.blogapirest.Controller;

import com.lta.blogapirest.DTO.PublicationDTO;
import com.lta.blogapirest.Service.Interface.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {
    @Autowired
    private IPublicationService ips;

    @GetMapping
    public ResponseEntity<?> GetAll(
            @RequestParam(value = "pageN",defaultValue = "0",required = false) int pageN,
            @RequestParam(value = "pageS",defaultValue = "10",required = false) int pageS){
        return ResponseEntity.ok(ips.GetAll(pageN,pageS));
    }

    @GetMapping("/rtr")
    public ResponseEntity<?> GetAllRTR(
            @RequestParam(value = "pageN",defaultValue = "0",required = false) int pageN,
            @RequestParam(value = "pageS",defaultValue = "10",required = false) int pageS,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sorDir){
        return ResponseEntity.ok(ips.GetAllRTR(pageN,pageS,sortBy,sorDir));
    }

    @GetMapping("/{idP}")
    public ResponseEntity<?> Get(@PathVariable("idP") Long idP){
        return ResponseEntity.ok().body(ips.Get(idP));
    }

    @PostMapping
    public ResponseEntity<?> Save(@RequestBody PublicationDTO pDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(ips.Save(pDTO));
    }

    @PutMapping("/{idP}")
    public ResponseEntity<?> Update(@PathVariable("idP") Long idP,@RequestBody PublicationDTO pDTO){
        return ResponseEntity.ok().body(ips.Update(pDTO,idP));
    }

    @DeleteMapping("/{idP}")
    public ResponseEntity<?> Delete(@PathVariable("idP") Long idP){
        ips.Delete(idP);
        return ResponseEntity.ok().build();
    }
}
