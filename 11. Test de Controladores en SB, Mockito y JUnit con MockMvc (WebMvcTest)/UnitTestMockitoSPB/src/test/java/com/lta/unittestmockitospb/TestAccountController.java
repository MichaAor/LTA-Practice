package com.lta.unittestmockitospb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lta.unittestmockitospb.Controller.AccountController;
import com.lta.unittestmockitospb.Model.Account;
import com.lta.unittestmockitospb.Model.DTO.TransactionDTO;
import com.lta.unittestmockitospb.Service.Interface.IAccountService;
import com.lta.unittestmockitospb.Tool.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)    //Prueba capa controllador y
public class TestAccountController {    //proporciona dependencias restantes mediante MOCKS
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAccountService ias;

    ObjectMapper om;    //de json a obj y visceversa
    @BeforeEach     //
    void config(){
        om = new ObjectMapper();
    }

    @Test
    void testViewDetails() throws Exception {
        when(ias.findById(1L)).thenReturn(Data.createAccount001().orElseThrow());

        mockMvc.perform(get("/api/accounts/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.person").value("Michael"))
                .andExpect(jsonPath("$.balance").value("1000"));

        verify(ias).findById(1L);
    }

    @Test
    void testTransferMoney() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO(1L,2L
                                                        , BigDecimal.valueOf(200),1L);

        System.out.println(om.writeValueAsString(transactionDTO));

        Map<String,Object> response = new HashMap<>();
        response.put("Date", LocalDate.now().toString());
        response.put("Status","Ok");
        response.put("Message","Transfer successful.");
        response.put("TransactionDTO",transactionDTO);

        System.out.println(om.writeValueAsString(transactionDTO));

        mockMvc.perform(post("/api/accounts/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(transactionDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.Message").value("Transfer successful."))
                .andExpect(jsonPath("$.TransactionDTO.sourceAccountID").value(transactionDTO.getSourceAccountID()))
                .andExpect(content().json(om.writeValueAsString(response)));
    }

    @Test
    void testListAccounts() throws Exception {
        List<Account> accounts = Arrays
                .asList(Data.createAccount001().orElseThrow()
                        ,Data.createAccount002().orElseThrow());
        when(ias.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/api/accounts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].person").value("Michael"))
                .andExpect(jsonPath("$[1].person").value("Jules"))
                .andExpect(jsonPath("$[0].balance").value("1000"))
                .andExpect(jsonPath("$[1].balance").value("2000"))
                .andExpect(jsonPath("$",hasSize(2)))

                .andExpect(content().json(om.writeValueAsString(accounts)));
        verify(ias).findAll();
    }

    @Test
    void testSaveAccount() throws Exception {
        Account account = new Account(null,"Baggio",BigDecimal.valueOf(4000));
        when(ias.save(any())).then(invocation ->{
            Account a = invocation.getArgument(0);
            a.setId(3L);
            return a;
        });

        mockMvc.perform(post("/api/accounts").contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(account)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(3)))
                .andExpect(jsonPath("$.person",is("Baggio")))
                .andExpect(jsonPath("$.balance",is(4000)));
        verify(ias).save(any());
    }
}
