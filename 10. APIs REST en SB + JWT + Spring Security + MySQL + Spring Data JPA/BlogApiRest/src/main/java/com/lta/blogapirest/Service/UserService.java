package com.lta.blogapirest.Service;

import com.lta.blogapirest.Model.User;
import com.lta.blogapirest.Repository.RoleRepository;
import com.lta.blogapirest.Repository.UserRepository;
import com.lta.blogapirest.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository ur;

    @Autowired
    private RoleRepository rr;

    @Override
    public void Register(User user) {
        user.setRoles(Collections.singleton(rr.findByName("ROLE_ADMIN").get()));
        ur.save(user);
    }

    @Override
    public String ExistsBy(String username) {
        if(ur.existsByUsername(username)){
            return "The username entered already exists.";
        }
        if(ur.existsByEmail(username)){
            return "The email entered already exists.";
        }
        return "";
    }
}
