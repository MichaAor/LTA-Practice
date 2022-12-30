package com.lta.apirestwebflux.Controller;

import com.lta.apirestwebflux.Documents.Client;
import com.lta.apirestwebflux.Service.Interface.IClientService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private IClientService ics;

    @Value("${config.uploads.path}")
    private String path;

    @PostMapping( "/saveComp")
    public Mono<ResponseEntity<Client>> saveComplete(Client client, @RequestPart FilePart file){
        client.setPhoto(UUID.randomUUID().toString() + "-" + file.filename()
                .replace(" ","")
                .replace(":","")
                .replace("//",""));
    return file.transferTo(new File(path + client.getPhoto())).then(ics.save(client))
            .map(c ->ResponseEntity.created(URI.create("/api/clients/".concat(c.getId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(c));
    }

    @PostMapping("/upload/{idC}")
    public Mono<ResponseEntity<Client>> uploadFile(@PathVariable String idC,@RequestPart FilePart file){
        return ics.get(idC).flatMap(c -> {
            c.setPhoto(UUID.randomUUID() + "-" + file.filename()
                    .replace(" ","")
                    .replace(":","")
                    .replace("//",""));
            return file.transferTo(new File(path + c.getPhoto())).then(ics.save(c));
        }).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Client>>> getAll(){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ics.getAll()));
    }

    @GetMapping("/{idC}")
    public Mono<ResponseEntity<Client>> get(@PathVariable String idC){
        return ics.get(idC).map(c->ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String,Object>>> save(@Valid @RequestBody Mono<Client> client){
        Map<String,Object> response = new HashMap<>();
    return client.flatMap(c->{
        return ics.save(c).map(cl->{        //Con map alteramos operadores o el mismo flujo
            response.put("client",cl);
            response.put("message","Client Save successful");
            response.put("timestamp",new Date());
        return ResponseEntity.created(URI.create("/api/clients/".concat(c.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        });
        }).onErrorResume(t->{
            return Mono.just(t).cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldError -> "The field: " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collectList().flatMap(list->{
                        response.put("errors",list);
                        response.put("timestamp",new Date());
                        response.put("status", HttpStatus.BAD_REQUEST.value());
                    return Mono.just(ResponseEntity.badRequest().body(response));
                    });
        });
    }

    @PutMapping("/{idC}")
    public Mono<ResponseEntity<Client>> update(@RequestBody Client client,@PathVariable String idC){
        return ics.get(idC).flatMap(c->{
            c.setName(client.getName());
            c.setSurname(client.getSurname());
            c.setAge(client.getAge());
            c.setSalary(client.getSalary());
            return ics.save(c);
        }).map(c->ResponseEntity.created(URI.create("/api/clients/".concat(c.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idC}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String idC){
        return ics.get(idC).flatMap(c->{
            return ics.delete(c).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        });

    }
}
