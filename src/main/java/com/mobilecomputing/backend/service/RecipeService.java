package com.mobilecomputing.backend.service;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RecipeService {

    private List<Recipe> randomRecipesCache = new ArrayList<>();
    private LocalDateTime lastChangedTime;

    @Autowired
    private RecipeRepository recipeRepository;

    // Fetch recipes by name or all recipes if no name is provided
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Fetch recipes by name
    public List<Recipe> getRecipesByName(String name) {
        return recipeRepository.findByNameContaining(name);
    }

    // Fetch recipes by instructions
    public List<Recipe> getRecipesByInstructions(String instructions) {
        return recipeRepository.findByInstructionsContaining(instructions);
    }

    // Fetch recipes by ingredients
    public List<Recipe> getRecipesByIngredients(String ingredients) {
        return recipeRepository.findByIngredientsContaining(ingredients);
    }

    // Fetch 5 random recipes, updated once every 24 hours
    public List<Recipe> getRandomRecipes() {
        LocalDateTime currentTime = LocalDateTime.now();
        if (lastChangedTime == null || ChronoUnit.HOURS.between(lastChangedTime, currentTime) >= 24) {
            List<Recipe> allRecipes = recipeRepository.findAll();
            randomRecipesCache.clear();

            if (allRecipes.size() <= 5) {
                randomRecipesCache.addAll(allRecipes);
            } else {
                Random random = new Random();
                while (randomRecipesCache.size() < 5) {
                    int randomIndex = random.nextInt(allRecipes.size());
                    Recipe randomRecipe = allRecipes.get(randomIndex);
                    if (!randomRecipesCache.contains(randomRecipe)) {
                        randomRecipesCache.add(randomRecipe);
                    }
                }
            }

            lastChangedTime = currentTime;
        }
        return new ArrayList<>(randomRecipesCache); // Return a copy to prevent external modification
    }
}
