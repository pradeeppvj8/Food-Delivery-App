package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.dto.RestaurantDTO;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user);

    Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurantRequest, User user) throws Exception;

    void deleteRestaurant(Long restaurantId) throws Exception;

    List<Restaurant> getAllRestaurants();

    List<Restaurant> searchRestaurants();

    Restaurant findRestaurantById(Long restaurantId) throws Exception;

    Restaurant getRestaurantByUserId(Long userId) throws Exception;

    RestaurantDTO addToFavourites(Long restaurantId, User user);

    Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
