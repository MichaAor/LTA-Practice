package com.lta.blogapirest.Controller;

import com.lta.blogapirest.DTO.LoginDTO;
import com.lta.blogapirest.DTO.RegisterDTO;
import com.lta.blogapirest.Model.User;
import com.lta.blogapirest.Security.JwtAuthResponseDTO;
import com.lta.blogapirest.Security.JwtTokenProvider;
import com.lta.blogapirest.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager am;

    @Autowired
    private IUserService ius;

    @Autowired
    private PasswordEncoder pe;

    @Autowired
    private JwtTokenProvider jwtTP;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDTO loginDTO){
        Authentication auth = am.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername()
                        ,loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        //Obtenemos token
        String token = jwtTP.generateToken(auth);
    return ResponseEntity.ok(new JwtAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody RegisterDTO registerDTO){
        if(ius.ExistsBy(registerDTO.getUsername()).isBlank()){
            User user = new User(registerDTO.getName(),
                    registerDTO.getUsername(),
                    registerDTO.getEmail(),
                    pe.encode(registerDTO.getPassword()));
            ius.Register(user);
            return new ResponseEntity<>("Register successful",HttpStatus.CREATED);
        }
        return new ResponseEntity<>(ius.ExistsBy(registerDTO.getUsername()),HttpStatus.BAD_REQUEST);
    }
}
