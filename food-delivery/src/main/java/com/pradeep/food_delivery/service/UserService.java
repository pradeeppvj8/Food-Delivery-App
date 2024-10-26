package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.model.User;

public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;
}
