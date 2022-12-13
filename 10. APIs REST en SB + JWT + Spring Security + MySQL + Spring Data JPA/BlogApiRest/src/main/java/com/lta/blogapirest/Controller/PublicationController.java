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
    public ResponseEntity<?> GetAll(){
        return ResponseEntity.ok(ips.GetAll());
    }


    @PostMapping
    public ResponseEntity<?> Save(@RequestBody PublicationDTO pDTO){
        pDTO = ips.Save(pDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(pDTO);
    }
}
