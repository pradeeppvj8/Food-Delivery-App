package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.config.JwtProvider;
import com.pradeep.food_delivery.enums.UserRole;
import com.pradeep.food_delivery.model.Cart;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.repository.CartRepository;
import com.pradeep.food_delivery.repository.UserRepository;
import com.pradeep.food_delivery.request.LoginRequest;
import com.pradeep.food_delivery.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

        if (userByEmail != null) {
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

    public AuthResponse signIn(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        String jwt = jwtProvider.generateToken(authentication);
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        String role = grantedAuthorities.isEmpty() ? null : grantedAuthorities.iterator().next().getAuthority();
        return AuthResponse.builder().jwt(jwt).message("Sign in successful").userRole(UserRole.valueOf(role)).build();
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username - " + username);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
    }
}
