package ru.mirea.platform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mirea.platform.config.JwtCore;
import ru.mirea.platform.entity.MyUser;
import ru.mirea.platform.entity.SignInRequest;
import ru.mirea.platform.entity.SignUpRequest;
import ru.mirea.platform.repository.UserRepository;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCore jwtCore;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неправильное имя пользователя или пароль");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsMyUserByUsername(signUpRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Логин уже существует!");
        }
        MyUser myUser = new MyUser();
        myUser.setUsername(signUpRequest.getUsername());
        myUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(myUser);
        return ResponseEntity.ok("Успешная регистрация");
    }
}
