package com.mobilecomputing.backend.service;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

    @PersistenceContext
    private EntityManager entityManager;

    // Fetch recipes by name or all recipes if no name is provided
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Fetch recipes by name
    public List<Recipe> getRecipesByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }

    // Fetch recipes by instructions
    public List<Recipe> getRecipesByInstructions(String instructions) {
        return recipeRepository.findByInstructionsContainingIgnoreCase(instructions);
    }

    // Fetch recipes by ingredients
    public List<Recipe> getRecipesByIngredients(String ingredients) {
        return recipeRepository.findByIngredientsContainingIgnoreCase(ingredients);
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

    // Fetch recipes containing all specified ingredients
    public List<Recipe> findByIngredientsContainingAll(List<String> ingredients) {
        /*CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = cb.createQuery(Recipe.class);
        Root<Recipe> recipe = query.from(Recipe.class);
        query.select(recipe).where(
                cb.and(
                        ingredients.stream()
                                .map(ingredient -> cb.isMember(ingredient, recipe.get("ingredients")))
                                .toArray(Predicate[]::new)
                )
        );
        return entityManager.createQuery(query).getResultList();
    */
    return recipeRepository.findByIngredientsContainingAll(ingredients);}
}
