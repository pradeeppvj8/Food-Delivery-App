package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.model.Category;
import com.pradeep.food_delivery.model.Food;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.repository.FoodRepository;
import com.pradeep.food_delivery.request.CreateFoodRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant) {
        Food food = new Food();

        food.setAvailable(true);
        food.setCreationDate(new Date());
        food.setCategory(createFoodRequest.getCategory());
        food.setImages(createFoodRequest.getImages());
        food.setName(createFoodRequest.getName());
        food.setDescription(createFoodRequest.getDescription());
        food.setPrice(createFoodRequest.getPrice());
        food.setSeasonal(createFoodRequest.isSeasonal());
        food.setIngredientsItems(createFoodRequest.getIngredientsItems());
        food.setVegetarian(createFoodRequest.isVegetarian());
        food.setRestaurant(restaurant);
        return foodRepository.save(food);
    }

    @Override
    public void deleteFound(Long foodId) throws Exception {
        foodRepository.deleteById(foodId);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyWord) {
        return List.of();
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return null;
    }

    @Override
    public Food updateFoodAvailability(Long foodId) throws Exception {
        return null;
    }
}
