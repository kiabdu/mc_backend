package com.mobilecomputing.backend.controller;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes(@RequestParam(required = false) String name){
        try {
            List<Recipe> recipes;
            if (name == null) {
                recipes = recipeRepository.findAll();
            } else {
                recipes = recipeRepository.findByNameContaining(name);
            }
            return ResponseEntity.ok(recipes);
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<List<Recipe>> getRecipeByName(String name){
        try {
            List<Recipe> recipes;
            recipes = recipeRepository.findByNameContaining(name);
            return ResponseEntity.ok(recipes);
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/instructions")
    public ResponseEntity<List<Recipe>> getRecipeByInstructions(String instructions){
        try {
            List<Recipe> recipes;
            recipes = recipeRepository.findByInstructionsContaining(instructions);
            return ResponseEntity.ok(recipes);
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Recipe>> getRecipeByIngredients(String ingredients){
        try {
            List<Recipe> recipes;
            recipes = recipeRepository.findByIngredientsContaining(ingredients);
            return ResponseEntity.ok(recipes);
        } catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}
