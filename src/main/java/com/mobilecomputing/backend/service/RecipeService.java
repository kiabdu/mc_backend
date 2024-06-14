package com.mobilecomputing.backend.service;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRandomRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        int lowerBound = 1;
        int upperBound = (int) recipeRepository.count();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            Optional<Recipe> optionalRecipe = recipeRepository.findById(randomNumber);
            optionalRecipe.ifPresent(recipes::add);
        }

        return recipes;
    }
}
