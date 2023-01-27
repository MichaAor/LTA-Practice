package com.lta.apilibrarybid.Repository;

import com.lta.apilibrarybid.Model.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PartyRepository extends CrudRepository<Party,Long>{
    Collection<Party> findAll();
}
