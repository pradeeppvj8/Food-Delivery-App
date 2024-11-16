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
import java.util.stream.Collectors;

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
    public void deleteFood(Long foodId) throws Exception {
        foodRepository.deleteById(foodId);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods);
        }

        if (isNonVeg) {
            foods = filterByNonVeg(foods);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods);
        }

        if (foodCategory != null && !foodCategory.isEmpty()) {
            foods = filterByFoodCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> food.getCategory() != null && food.getCategory().getName().equals(foodCategory))
                .collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods) {
        return foods.stream().filter(Food::isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods) {
        return foods.stream().filter(Food::isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyWord) {
        return foodRepository.searchFood(keyWord);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return foodRepository.findById(foodId).orElseThrow(() -> new Exception("Food doesn't exist"));
    }

    @Override
    public Food updateFoodAvailability(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
