package com.lta.apirestwebflux.Service;

import com.lta.apirestwebflux.Documents.Client;
import com.lta.apirestwebflux.Repository.ClientRepository;
import com.lta.apirestwebflux.Service.Interface.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements IClientService {
    @Autowired
    private ClientRepository cr;

    @Override
    public Flux<Client> getAll() {
        return cr.findAll();
    }

    @Override
    public Mono<Client> get(String id) {
        return cr.findById(id);
    }

    @Override
    public Mono<Client> save(Client client) {
        return cr.save(client);
    }

    @Override
    public Mono<Void> delete(Client client) {
        return cr.delete(client);
    }
}
