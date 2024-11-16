package com.pradeep.food_delivery.controller;

import com.pradeep.food_delivery.model.Food;
import com.pradeep.food_delivery.service.FoodService;
import com.pradeep.food_delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @GetMapping("/search-food")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword) throws Exception {
        List<Food> foods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant-food/{restaurantId}")
    public ResponseEntity<List<Food>> findRestaurantsFood(@RequestParam boolean isVegetarian,
                                                          @RequestParam boolean isNonVeg,
                                                          @RequestParam boolean isSeasonal,
                                                          @RequestParam(required = false) String category,
                                                          @PathVariable Long restaurantId) throws Exception {
        List<Food> foods = foodService.getRestaurantsFood(restaurantId, isVegetarian, isNonVeg, isSeasonal, category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
