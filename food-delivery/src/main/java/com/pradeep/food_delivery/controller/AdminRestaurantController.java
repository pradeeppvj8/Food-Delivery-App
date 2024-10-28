package com.pradeep.food_delivery.controller;

import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.request.CreateRestaurantRequest;
import com.pradeep.food_delivery.response.MessageResponse;
import com.pradeep.food_delivery.service.RestaurantService;
import com.pradeep.food_delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @PostMapping("/create-restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(createRestaurantRequest, user);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/update-restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long restaurantId) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, createRestaurantRequest, user);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/delete-restaurant/{restaurantId}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@PathVariable Long restaurantId) throws Exception {
        restaurantService.deleteRestaurant(restaurantId);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/update-status/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/find-by-user")
    public ResponseEntity<Restaurant> findRestaurantByUser(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
