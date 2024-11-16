package com.pradeep.food_delivery.controller;

import com.pradeep.food_delivery.model.Food;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.request.CreateFoodRequest;
import com.pradeep.food_delivery.response.MessageResponse;
import com.pradeep.food_delivery.service.FoodService;
import com.pradeep.food_delivery.service.RestaurantService;
import com.pradeep.food_delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
@RequiredArgsConstructor
public class AdminFoodController {
    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @PostMapping("/create-food")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest createFoodRequest) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(createFoodRequest.getRestaurantId());
        Food food = foodService.createFood(createFoodRequest, createFoodRequest.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-food/{foodId}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long foodId) throws Exception {
        foodService.deleteFood(foodId);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Food deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PatchMapping("/update-food-availability/{foodId}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long foodId) throws Exception {
        Food food = foodService.updateFoodAvailability(foodId);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
