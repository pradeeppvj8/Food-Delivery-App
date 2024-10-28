package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.dto.RestaurantDTO;
import com.pradeep.food_delivery.model.Address;
import com.pradeep.food_delivery.model.Restaurant;
import com.pradeep.food_delivery.model.User;
import com.pradeep.food_delivery.repository.AddressRepository;
import com.pradeep.food_delivery.repository.RestaurantRepository;
import com.pradeep.food_delivery.repository.UserRepository;
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
    private final UserRepository userRepository;

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

        if (updateRestaurantRequest.getName() != null) {
            restaurant.setName(updateRestaurantRequest.getName());
        }

        if (updateRestaurantRequest.getAddress() != null) {
            restaurant.setAddress(updateRestaurantRequest.getAddress());
        }

        if (updateRestaurantRequest.getContactInformation() != null) {
            restaurant.setContactInformation(updateRestaurantRequest.getContactInformation());
        }

        if (updateRestaurantRequest.getOpeningHours() != null) {
            restaurant.setOpeningHours(updateRestaurantRequest.getOpeningHours());
        }

        if (updateRestaurantRequest.getImages() != null) {
            restaurant.setImages(updateRestaurantRequest.getImages());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyWord) {
        return restaurantRepository.findBySearchQuery(keyWord);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new Exception("Restaurant not found with ID - " + restaurantId));
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        return restaurantRepository.findByOwnerId(userId).orElseThrow(() -> new Exception("Restaurant not found with User ID - " + userId));
    }

    @Override
    public RestaurantDTO addToFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setImages(restaurant.getImages());
        restaurantDTO.setTitle(restaurant.getName());

        if (user.getFavourites() != null && !user.getFavourites().contains(restaurantDTO)) {
            user.getFavourites().add(restaurantDTO);
        }
        userRepository.save(user);
        return restaurantDTO;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
