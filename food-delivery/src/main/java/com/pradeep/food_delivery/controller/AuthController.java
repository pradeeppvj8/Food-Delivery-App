package com.pradeep.food_delivery.controller;

import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.response.AuthResponse;
import com.pradeep.food_delivery.service.AuthService;
import com.pradeep.food_delivery.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CustomUserDetailsService customUserDetailsService;

    public ResponseEntity<AuthResponse> createAuthHandle(@RequestBody User user) throws Exception {
        AuthResponse authResponse = authService.createAuthHandle(user);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
