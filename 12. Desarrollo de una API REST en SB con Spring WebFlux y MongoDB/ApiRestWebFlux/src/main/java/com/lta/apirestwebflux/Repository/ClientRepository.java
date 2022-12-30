package com.lta.apirestwebflux.Repository;

import com.lta.apirestwebflux.Documents.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client,String> {
}
