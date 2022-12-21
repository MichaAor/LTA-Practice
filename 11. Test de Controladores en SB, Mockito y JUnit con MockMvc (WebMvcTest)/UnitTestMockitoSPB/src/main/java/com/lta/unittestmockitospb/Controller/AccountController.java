package com.lta.unittestmockitospb.Controller;

import com.lta.unittestmockitospb.Model.Account;
import com.lta.unittestmockitospb.Model.DTO.TransactionDTO;
import com.lta.unittestmockitospb.Service.Interface.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService ias;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Account> listAll(){
        return ias.findAll();
    }

    @GetMapping("/{idA}")
    @ResponseStatus(code = HttpStatus.OK)
    public Account viewDetails(@PathVariable Long idA){
        return ias.findById(idA);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransactionDTO transactionDTO){
        ias.transfer(transactionDTO.getSourceAccountID()
                ,transactionDTO.getTargetAccountID()
                ,transactionDTO.getAmount()
                ,transactionDTO.getBankID());
        Map<String,Object> response = new HashMap<>();
        response.put("Date", LocalDate.now().toString());
        response.put("Status","Ok");
        response.put("Message","Transfer successful.");
        response.put("TransactionDTO",transactionDTO);
    return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account save(@RequestBody Account account){
        return ias.save(account);
    }


}
