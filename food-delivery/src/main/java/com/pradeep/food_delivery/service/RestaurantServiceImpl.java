package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.dto.RestaurantDTO;
import com.pradeep.food_delivery.model.Address;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.repository.AddressRepository;
import com.pradeep.food_delivery.repository.RestaurantRepository;
import com.pradeep.food_delivery.request.CreateRestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user) {
        Address address = addressRepository.save(createRestaurantRequest.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setName(createRestaurantRequest.getName());
        restaurant.setDescription(createRestaurantRequest.getDescription());
        restaurant.setImages(createRestaurantRequest.getImages());
        restaurant.setCuisineType(createRestaurantRequest.getCuisineType());
        restaurant.setContactInformation(createRestaurantRequest.getContactInformation());
        restaurant.setOpen(true);
        restaurant.setOpeningHours(createRestaurantRequest.getOpeningHours());
        restaurant.setOwner(user);
        restaurant.setRegistrationDate(LocalDateTime.now());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurantRequest, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (updateRestaurantRequest.getCuisineType() != null) {
            restaurant.setCuisineType(updateRestaurantRequest.getCuisineType());
        }

        if (updateRestaurantRequest.getDescription() != null) {
            restaurant.setDescription(updateRestaurantRequest.getDescription());
        }

        return null;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public List<Restaurant> searchRestaurants() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public RestaurantDTO addToFavourites(Long restaurantId, User user) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        return null;
    }
}
