package com.pradeep.food_delivery.repository;

import com.pradeep.food_delivery.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
