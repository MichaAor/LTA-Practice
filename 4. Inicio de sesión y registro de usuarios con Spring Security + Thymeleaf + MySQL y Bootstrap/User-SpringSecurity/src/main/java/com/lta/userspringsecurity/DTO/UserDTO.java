package com.lta.userspringsecurity.DTO;

import lombok.Data;
@Data
public class UserDTO{
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String password;
}
