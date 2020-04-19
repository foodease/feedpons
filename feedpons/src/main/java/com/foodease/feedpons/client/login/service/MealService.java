package com.foodease.feedpons.client.login.service;

import com.foodease.feedpons.client.login.model.Meal;
import com.foodease.feedpons.client.login.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    public Meal findByBarcode(String barcode){
        return mealRepository.findByBarcode(barcode);
    }



}
