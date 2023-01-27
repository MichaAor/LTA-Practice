package com.lta.apilibrarybid.Repository;

import com.lta.apilibrarybid.Model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
    Collection<Person> findAll();
}
