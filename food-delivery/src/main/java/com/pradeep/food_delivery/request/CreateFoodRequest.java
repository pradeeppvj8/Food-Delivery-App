package com.pradeep.food_delivery.request;

import com.pradeep.food_delivery.model.Category;
import com.pradeep.food_delivery.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;

    private String description;

    private Long price;

    private Category category;

    private List<String> images;

    private Long restaurantId;

    private boolean isVegetarian;

    private boolean isSeasonal;

    private List<IngredientsItem> ingredientsItems;
}
