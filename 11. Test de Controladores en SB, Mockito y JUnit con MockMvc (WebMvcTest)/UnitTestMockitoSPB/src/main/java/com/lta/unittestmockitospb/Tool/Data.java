package com.lta.unittestmockitospb.Tool;

import com.lta.unittestmockitospb.Model.Account;
import com.lta.unittestmockitospb.Model.Bank;

import java.math.BigDecimal;
import java.util.Optional;

public class Data {
    public static Optional<Account> createAccount001(){
        return Optional.of(new Account(1L,"Michael",new BigDecimal("1000")));
    }

    public static Optional<Account> createAccount002(){
        return Optional.of(new Account(2L,"Jules",new BigDecimal("2000")));
    }

    public static Optional<Bank> createBank(){
        return Optional.of(new Bank(1L,"H1S3",0));
    }
}
