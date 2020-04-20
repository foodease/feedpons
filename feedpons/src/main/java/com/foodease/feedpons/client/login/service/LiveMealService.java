package com.foodease.feedpons.client.login.service;

import com.foodease.feedpons.client.login.model.LiveMeal;
import com.foodease.feedpons.client.login.repository.LiveMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveMealService {

    private LiveMealRepository liveMealRepository;

    @Autowired
    public LiveMealService(LiveMealRepository liveMealRepository) {
        this.liveMealRepository = liveMealRepository;
    }

    public LiveMeal saveLiveMeal(LiveMeal liveMeal, String meal, String pickupType, String status){
        liveMeal.setItemName(meal);
        liveMeal.setPickupType(pickupType);
        liveMeal.setStatus(status);
        return liveMealRepository.save(liveMeal);
    }

    public List<LiveMeal> findAll(){
        return liveMealRepository.findAll();
    }


    public LiveMeal findByItemName(String itemName){
        return liveMealRepository.findByItemName(itemName);
    }


    public void saveLiveMeal(LiveMeal liveMeal) {
        liveMealRepository.save(liveMeal);
    }
}
