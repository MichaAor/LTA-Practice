package com.lta.unittestmockitospb.Service.Interface;

import com.lta.unittestmockitospb.Model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    Account findById(Long idA);
    Account save(Account account);
    int checkTotalTransfer(Long idB);
    BigDecimal checkBalance(Long idA);
    void transfer(Long idSA,Long idTA,BigDecimal amount,Long idB);
}
