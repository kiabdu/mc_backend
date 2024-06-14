package com.mobilecomputing.backend.controller;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipe")
    public ResponseEntity<List<Recipe>> getRecipeByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String ingredients,
            @RequestParam(required = false) String instructions){
        List<Recipe> recipes;

        try {
            if(name != null){
                recipes = recipeService.getRecipesByName(name);
            } else if(ingredients != null){
                recipes = recipeService.getRecipesByIngredients(ingredients);
            } else if(instructions != null){
                recipes = recipeService.getRecipesByInstructions(instructions);
            } else {
                recipes = recipeService.getAllRecipes();
            }

            return ResponseEntity.ok(recipes);
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/random")
    public ResponseEntity<List<Recipe>> getRandomRecipes() {
        try {
            List<Recipe> recipes = recipeService.getRandomRecipes();
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
