package com.foodease.feedpons.client.login.repository;

import com.foodease.feedpons.client.login.model.LiveMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveMealRepository extends JpaRepository<LiveMeal, String> {

    List<LiveMeal> findAll();

}
