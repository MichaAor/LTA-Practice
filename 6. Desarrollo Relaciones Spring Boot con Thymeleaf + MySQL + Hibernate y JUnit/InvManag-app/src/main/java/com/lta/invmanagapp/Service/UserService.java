package com.lta.invmanagapp.Service;

import com.lta.invmanagapp.Model.User;
import com.lta.invmanagapp.Repository.UserRepository;
import com.lta.invmanagapp.Service.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository ur;

    @Override
    public List<User> GetAll() {
        return ur.findAll();
    }

    @Override
    public Optional<User> Get(Long idU) {
        return ur.findById(idU);
    }

    @Override
    public void Save(User user) {
        ur.save(user);
    }

    @Override
    public void Update(User user) {
        ur.save(user);
    }

    @Override
    public void Delete (Long idU){
        ur.deleteById(idU);
    }
}
