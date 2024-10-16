package com.pradeep.food_delivery.dto;

import com.pradeep.food_delivery.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Embeddable
public class RestaurantDTO {

    private Long id;

    private String title;

    @Column(length = 1000)
    private List<String> images = new ArrayList<>();

    private String description;
}
