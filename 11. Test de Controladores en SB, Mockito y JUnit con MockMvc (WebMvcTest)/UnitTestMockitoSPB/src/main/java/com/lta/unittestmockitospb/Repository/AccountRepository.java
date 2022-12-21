package com.lta.unittestmockitospb.Repository;

import com.lta.unittestmockitospb.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    public Optional<Account> findByPerson(String person);
}
