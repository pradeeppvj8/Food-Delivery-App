package com.pradeep.food_delivery.controller;

import com.pradeep.food_delivery.dto.RestaurantDTO;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.service.RestaurantService;
import com.pradeep.food_delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @GetMapping("/search-by-kw")
    public ResponseEntity<List<Restaurant>> findRestaurantsByQuery(@RequestParam String keyWord) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(keyWord);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{restaurantId}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/add-to-favourites/{restaurantId}")
    public ResponseEntity<RestaurantDTO> addToFavourites(@PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurantDTO = restaurantService.addToFavourites(restaurantId, user);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }
}
