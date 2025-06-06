package com.LT1Init.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LT1Init.backend.dto.LoginRequest;
import com.LT1Init.backend.dto.RegisterRequest;
import com.LT1Init.backend.service.UserService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginRequest> loginAPI(@RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> registerAPI(@RequestBody RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }
}
