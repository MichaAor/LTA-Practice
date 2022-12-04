package com.lta.invmanagapp.Service;

import com.lta.invmanagapp.Model.Role;
import com.lta.invmanagapp.Repository.RoleRepository;
import com.lta.invmanagapp.Service.IService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository rr;


    @Override
    public List<Role> GetAll() {
        return rr.findAll();
    }

    @Override
    public Optional<Role> Get(Long idR) {
        return rr.findById(idR);
    }

    @Override
    public void Save(Role role) {
        rr.save(role);
    }

    @Override
    public void Update(Role role) {
        rr.save(role);
    }

    @Override
    public void Delete(Long idR) {
        rr.deleteById(idR);
    }
}
