package com.foodease.feedpons.client.login.repository;

import com.foodease.feedpons.client.login.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, String>{
    Meal findByBarcode(String barcode);
}
