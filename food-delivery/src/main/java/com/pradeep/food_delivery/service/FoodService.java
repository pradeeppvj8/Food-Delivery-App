package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.model.Category;
import com.pradeep.food_delivery.model.Food;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory);

    List<Food> searchFood(String keyWord);

    Food findFoodById(Long foodId) throws Exception;

    Food updateFoodAvailability(Long foodId) throws Exception;
}
