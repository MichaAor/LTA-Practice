package com.lta.invmanagapp.Service.IService;

import com.lta.invmanagapp.Model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role>GetAll();
    Optional<Role>Get(Long idR);
    void Save(Role role);
    void Update(Role role);
    void Delete(Long idR);
}
