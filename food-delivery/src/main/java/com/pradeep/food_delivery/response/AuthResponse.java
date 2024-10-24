package com.pradeep.food_delivery.response;

import com.pradeep.food_delivery.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserRole userRole;
}
