package com.lta.userspringsecurity.Service;

import com.lta.userspringsecurity.DTO.UserDTO;
import com.lta.userspringsecurity.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {
    List<User> GetAll();
    Optional<User> Get(Long idU);
    void Save(UserDTO userDTO);
    void Update(User user);
    void Delete(Long idU);
}
