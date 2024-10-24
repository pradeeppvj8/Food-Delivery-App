package com.pradeep.food_delivery.repository;

import com.pradeep.food_delivery.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
