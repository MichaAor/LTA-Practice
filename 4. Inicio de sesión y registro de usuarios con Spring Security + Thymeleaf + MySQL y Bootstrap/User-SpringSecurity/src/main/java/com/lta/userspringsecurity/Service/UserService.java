package com.lta.userspringsecurity.Service;

import com.lta.userspringsecurity.DTO.UserDTO;
import com.lta.userspringsecurity.Model.Role;
import com.lta.userspringsecurity.Model.User;
import com.lta.userspringsecurity.Repository.UserRepository;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository ur;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> GetAll() {
        return ur.findAll();
    }

    @Override
    public Optional<User> Get(Long idU) {
        return ur.findById(idU);
    }

    @Override
    public void Save(UserDTO userDTO) {
        User user = new User(userDTO.getName(),userDTO.getLastname()
                            ,userDTO.getEmail(),passwordEncoder.encode(userDTO.getPassword())
                            ,List.of(new Role("ROLE_USER")));
        ur.save(user);
    }

    @Override
    public void Update(User user) {
        ur.save(user);
    }

    @Override
    public void Delete(Long idU) {
        ur.deleteById(idU);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userN = ur.findByEmail(username);
        if(userN == null){
            throw new UsernameNotFoundException("User or password invalid");
        }
        return new org.springframework.security.core.userdetails.User(userN.getEmail(),userN.getPassword(),mapperAuthoritiesToRoles(userN.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapperAuthoritiesToRoles(Collection<Role> roles){
        return roles.stream()
                .map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
