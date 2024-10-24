package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.config.JwtProvider;
import com.pradeep.food_delivery.model.Cart;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.repository.CartRepository;
import com.pradeep.food_delivery.repository.UserRepository;
import com.pradeep.food_delivery.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final CartRepository cartRepository;


    public AuthResponse createAuthHandle(User user) throws Exception {
        User userByEmail = userRepository.findByEmail(user.getEmail());

        if(userByEmail != null) {
            throw new Exception("User with this email already exists - " + user.getEmail());
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());
        newUser.setUserRole(user.getUserRole());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(newUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return AuthResponse.builder().jwt(jwt).message("Registration Successful").userRole(savedUser.getUserRole()).build();
    }
}
