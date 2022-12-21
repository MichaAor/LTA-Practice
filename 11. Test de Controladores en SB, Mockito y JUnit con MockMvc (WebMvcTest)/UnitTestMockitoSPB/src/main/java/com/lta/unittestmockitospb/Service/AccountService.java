package com.lta.unittestmockitospb.Service;

import com.lta.unittestmockitospb.Model.Account;
import com.lta.unittestmockitospb.Model.Bank;
import com.lta.unittestmockitospb.Repository.AccountRepository;
import com.lta.unittestmockitospb.Repository.BankRepository;
import com.lta.unittestmockitospb.Service.Interface.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository ar;

    @Autowired
    private BankRepository br;

    @Override
    @Transactional(readOnly = true) //solo lectura
    public List<Account> findAll() {
        return ar.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Long idA) {
        return ar.findById(idA).orElseThrow();
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return ar.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public int checkTotalTransfer(Long idB) {
        Bank bank = br.findById(idB).orElseThrow();
        return bank.getTotalTransfers();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal checkBalance(Long idA) {
        Account account = ar.findById(idA).orElseThrow();
        return account.getBalance();
    }

    @Override
    public void transfer(Long idSA, Long idTA, BigDecimal amount, Long idB) {

        Account sourceAccount = ar.findById(idSA).orElseThrow();
        sourceAccount.debit(amount);
        ar.save(sourceAccount);

        Account targetAccount = ar.findById(idTA).orElseThrow();
        targetAccount.acredit(amount);
        ar.save(targetAccount);

        Bank bank = br.findById(idB).orElseThrow();
        bank.setTotalTransfers(bank.getTotalTransfers()+1);
        br.save(bank);
    }
}
