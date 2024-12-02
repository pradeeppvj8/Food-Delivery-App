package com.pradeep.food_delivery.service;

import com.pradeep.food_delivery.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(String name, Long userId) throws Exception;

    List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception;

    Category findCategoryById(Long categoryId) throws Exception;
}
