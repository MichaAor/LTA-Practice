package com.lta.blogapirest.Controller;

import com.lta.blogapirest.DTO.PublicationDTO;
import com.lta.blogapirest.Service.Interface.IPublicationService;
import com.lta.blogapirest.Utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @RequestParam(value = "pageN",defaultValue = AppConstants.D_PAGE_N
                    ,required = false) int pageN,
            @RequestParam(value = "pageS",defaultValue = AppConstants.D_PAGE_S
                    ,required = false) int pageS,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.D_SORT_BY
                    ,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.D_SORT_DIR
                    ,required = false)String sorDir){
        return ResponseEntity.ok(ips.GetAllRTR(pageN,pageS,sortBy,sorDir));
    }

    @GetMapping("/{idP}")
    public ResponseEntity<?> Get(@PathVariable("idP") Long idP){
        return ResponseEntity.ok().body(ips.Get(idP));
    }

    @PreAuthorize("hasRole('ADMIN')")   //filtra el acceso segun role
    @PostMapping
    public ResponseEntity<?> Save(@Valid @RequestBody PublicationDTO pDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(ips.Save(pDTO));
    }

    @PutMapping("/{idP}")
    public ResponseEntity<?> Update(@PathVariable("idP") Long idP,@Valid @RequestBody PublicationDTO pDTO){
        return ResponseEntity.ok().body(ips.Update(pDTO,idP));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idP}")
    public ResponseEntity<?> Delete(@PathVariable("idP") Long idP){
        ips.Delete(idP);
        return ResponseEntity.ok().build();
    }
}
