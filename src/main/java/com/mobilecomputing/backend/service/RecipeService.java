package com.mobilecomputing.backend.service;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RecipeService {
    private List<Recipe> recipes = new ArrayList<>();
    private LocalDateTime lastChangedTime;
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRandomRecipes(){
        LocalDateTime currentTime = LocalDateTime.now();

        if(lastChangedTime == null || ChronoUnit.HOURS.between(lastChangedTime, currentTime) >= 24) {
            int lowerBound = 1;
            int upperBound = (int) recipeRepository.count();
            Random random = new Random();

            recipes.clear();

            for (int i = 0; i < 5; i++) {
                int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                Optional<Recipe> optionalRecipe = recipeRepository.findById(randomNumber);
                optionalRecipe.ifPresent(recipes::add);
            }
        }

        lastChangedTime = currentTime;

        return recipes;
    }
}