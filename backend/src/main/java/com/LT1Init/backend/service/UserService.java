package com.LT1Init.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LT1Init.backend.dto.LoginRequest;
import com.LT1Init.backend.dto.RegisterRequest;
import com.LT1Init.backend.model.Role;
import com.LT1Init.backend.model.User;
import com.LT1Init.backend.repository.UserRepository;
import com.LT1Init.backend.security.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public RegisterRequest register(RegisterRequest request){
        try {
            if(userRepository.existsByUsername(request.getUsername())){
                throw new RuntimeException("Bu kullanıcı adı zaten alınmış! "+ request.getUsername());
            }
            if(request.getPassword().length()<5){
                throw new RuntimeException("Şifre en az 6 karakter olmalı!");
            }

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setRole(Role.USER);
            
            User saved = userRepository.save(user);
            
            UserDetails UserDetails = org.springframework.security.core.userdetails.User
            .withUsername(saved.getUsername())
            .password(saved.getPassword())
            .roles(saved.getRole().name())
            .build();

            // token kurma
            String token = jwtTokenUtil.generateToken(UserDetails);

            return new RegisterRequest(
                saved.getId(),
                saved.getUsername(),
                null,
                saved.getEmail(),
                saved.getRole().name(),
                token
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error!"+e.getMessage());
        }
    }

    public LoginRequest login(LoginRequest request){
       try {
            Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
            );
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            return new LoginRequest(
                userDetails.getUsername(),
                null,
                token
            );
       } catch (RuntimeException e) {

            e.printStackTrace();
            throw new RuntimeException("Internal Server Error! "+ e.getMessage(),e);  
       }

    }

    
}