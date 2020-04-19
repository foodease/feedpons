package com.foodease.feedpons.client.login.service;

import com.foodease.feedpons.client.login.model.ActiveMeal;
import com.foodease.feedpons.client.login.repository.ActiveMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActiveMealService {

    private ActiveMealRepository activeMealRepository;

    @Autowired
    public ActiveMealService(ActiveMealRepository activeMealRepository) {
        this.activeMealRepository = activeMealRepository;
    }


    public List<ActiveMeal> findAll(){
        return activeMealRepository.findAll();
    }

    public ActiveMeal saveActiveMeal(ActiveMeal activeMeal, String meal){
        activeMeal.setItemName(meal);
        return activeMealRepository.save(activeMeal);

    }

    @Transactional
    public void deleteActiveMeal(String meal){
        activeMealRepository.deleteByItemName(meal);
    }

}
