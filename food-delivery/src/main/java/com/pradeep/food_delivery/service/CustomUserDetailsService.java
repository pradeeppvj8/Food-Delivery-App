package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.enums.UserRole;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found with email - " + username);
        }

        UserRole userRole = user.getUserRole();

        if(userRole == null) {
            userRole = UserRole.ROLE_CUSTOMER;
        }

        return null;
    }
}
