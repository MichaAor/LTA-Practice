package com.lta.unittestmockitospb.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private Long sourceAccountID;
    private Long targetAccountID;
    private BigDecimal amount;
    private Long bankID;

}
