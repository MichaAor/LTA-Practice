package com.lta.apirestwebflux.Service.Interface;

import com.lta.apirestwebflux.Documents.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Flux<Client> getAll();
    Mono<Client> get(String id);
    Mono<Client> save(Client client);
    Mono<Void> delete(Client client);
}
