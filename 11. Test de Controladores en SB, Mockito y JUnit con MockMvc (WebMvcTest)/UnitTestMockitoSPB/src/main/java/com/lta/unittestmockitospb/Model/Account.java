package com.lta.unittestmockitospb.Model;

import com.lta.unittestmockitospb.Exception.InsufficientBalance;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String person;
    private BigDecimal balance;

    public void debit(BigDecimal amount){
        BigDecimal updBal = this.balance.subtract(amount);
        if(updBal.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalance("You do not have sufficient balance to carry out the operation");
        }
        this.balance = updBal;
    }

    public void acredit(BigDecimal amount){
        this.balance.add(amount);
    }

}
