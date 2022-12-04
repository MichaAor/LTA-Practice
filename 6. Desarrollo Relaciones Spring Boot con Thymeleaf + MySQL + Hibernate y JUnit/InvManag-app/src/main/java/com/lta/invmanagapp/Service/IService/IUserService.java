package com.lta.invmanagapp.Service.IService;

import com.lta.invmanagapp.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> GetAll();
    Optional<User> Get(Long idU);
    void Save(User user);
    void Update(User user);
    void Delete(Long idU);
}
